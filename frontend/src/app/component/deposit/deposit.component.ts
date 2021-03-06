import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Account} from "../../shared/models/account";
import {AccountService} from "../../service/account.service";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AccountCommand} from "../../shared/models/account-command";
import {PositiveNumberValidator} from "../../shared/validators/positive-number";

@Component({
  selector: 'deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  title: string;

  form: FormGroup;
  accounts: Account[];
  error: any;

  returnUrl: string;
  curAccountCurrency: string;

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    let routeUrl = this.route.snapshot.url[0].path;
    this.title = routeUrl.charAt(0).toUpperCase() + routeUrl.slice(1);
    this.form = this.formBuilder.group({
      account : ['', Validators.compose([Validators.required])],
      amount : ['', Validators.compose([Validators.required, PositiveNumberValidator.mustBePositive])]
    });
    this.accountService.getAllAccounts().subscribe(
      res => {
        this.accounts = res;
      }
    );
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get amount() {
    return this.form.get("amount");
  }

  onSubmit() {
    let action = this.route.snapshot.url[0].path;
    let accountCommand = new AccountCommand(action, this.form.get("account").value, this.form.get("amount").value);
    this.accountService.deposit(accountCommand)
      .mergeMap(() => this.accountService.getAllAccounts()).subscribe(
      res => {
        this.form.controls["amount"].reset();
        this.form.controls["amount"].setErrors(null);
        this.accounts = res;
        this.accounts.forEach((item, index) => {
          if (item.id == accountCommand.accountId) {
            this.form.get("account").setValue(this.accounts[index]);
          }
        })
        this.error = null;
      }, err => {
        if (err.status == 400) {
          this.error = err.error.errorMessage
        } else {
          this.error = 'Opps, server error !'
        }
      }
    );
  }

  cancel() {
    this.router.navigate([this.returnUrl])
  }

  onAccountChange() {
    let val = this.form.controls['account'].value.currency.name.toLowerCase() == 'rur' ? 'rub' : this.form.controls['account'].value.currency.name.toLowerCase()
    this.curAccountCurrency = val;
  }

}
