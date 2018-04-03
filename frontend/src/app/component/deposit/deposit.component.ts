import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Account} from "../../shared/models/account";
import {AccountService} from "../../service/account.service";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AccountCommand} from "../../shared/models/account-command";

@Component({
  selector: 'deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  form: FormGroup;
  accounts: Account[];
  error: any;

  returnUrl: string;

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      account : ['', Validators.compose([Validators.required])],
      amount : ['', Validators.compose([Validators.required])]
    });
    this.accountService.getAllAccounts().subscribe(
      res => {
        this.accounts = res;
      }
    );
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    let accountCommand = new AccountCommand(this.form.get("account").value, this.form.get("amount").value);
    this.accountService.deposit(accountCommand)
      .mergeMap(() => this.accountService.getAllAccounts()).subscribe(
      res => {
        this.form.get("amount").setValue(0);
        this.accounts = res;
        this.accounts.forEach((item, index) => {
          if (item.id == accountCommand.accountId) {
            this.form.get("account").setValue(this.accounts[index]);
          }
        })
      }, err => {
        this.error = 'Opps, server error !'
      }
    );
  }

  cancel() {
    this.router.navigate([this.returnUrl])
  }

}
