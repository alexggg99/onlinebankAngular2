import { Component, OnInit } from '@angular/core';
import {
  UserService,
  AuthService,
  CurrencyService
} from '../../service';
import { Router, ActivatedRoute  } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Currency } from '../../shared/models/currency'
import {AccountService} from "../../service/account.service";

@Component({
  selector: 'create-account',
  templateUrl: './create.account.component.html',
  styleUrls: ['./create.account.component.scss']
})
export class CreateAccountComponent implements OnInit {

  form: FormGroup;
  submitted: boolean = false;

  currencies: Currency[];
  error: string;

  returnUrl: string;

  constructor(
    private userService: UserService,
    private currencyService: CurrencyService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private accountService: AccountService
  ) { }

  ngOnInit(){
    this.form = this.formBuilder.group({
      currency: ['', Validators.compose([Validators.required])]
    });
    this.currencyService.getCurrencies().subscribe(
      res => {
        this.currencies = res;
      }
    )
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.accountService.createPrimaryAccount(this.form.get('currency').value).subscribe(
      res => {
        this.router.navigate(['/']);
      }, err => {
        this.error = err.statusText
      }
    )
  }

  cancel() {
    this.router.navigate([this.returnUrl])
  }

}
