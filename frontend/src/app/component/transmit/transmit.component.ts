import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Account} from "../../shared/models/account";
import {AccountService} from "../../service/account.service";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PositiveNumberValidator} from "../../shared/validators/positive-number";
import {TransferService} from "../../service/transfer.service";

@Component({
  selector: 'transmit',
  templateUrl: './transmit.component.html',
  styleUrls: ['./transmit.component.scss']
})
export class TransmitComponent implements OnInit {

  title: string;

  form: FormGroup;
  accountsFrom: Account[];
  error: any;

  returnUrl: string;

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private trasmitService: TransferService
  ) { }

  ngOnInit() {
    let routeUrl = this.route.snapshot.url[0].path;
    this.title = routeUrl.charAt(0).toUpperCase() + routeUrl.slice(1);
    this.form = this.formBuilder.group({
      accountFrom : ['', Validators.compose([Validators.required])],
      accountTo : ['', Validators.compose([Validators.required])],
      amount : ['', Validators.compose([Validators.required, PositiveNumberValidator.mustBePositive])]
    });
    this.accountService.getAllAccounts().subscribe(
      res => {
        this.accountsFrom = res;
      }
    );
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get amount() {
    return this.form.get("amount");
  }

  onSubmit() {
    this.trasmitService.transfer(this.form.get("accountFrom").value, this.form.get("accountTo").value, this.form.get("amount").value)
      .subscribe(res => {
        this.router.navigate([this.returnUrl])
      }, err => {
        if (err.status == 400) {
          this.error = err.error.errorMessage
        } else {
          this.error = 'Opps, server error !'
        }
      })
  }

  cancel() {
    this.router.navigate([this.returnUrl])
  }

  onAccountFromChange() {
    this.form.controls['accountTo'].reset();
  }

}
