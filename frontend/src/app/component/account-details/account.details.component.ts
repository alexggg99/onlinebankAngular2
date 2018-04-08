import {Component, OnInit} from '@angular/core';
import {
  UserService,
  AuthService,
  CurrencyService
} from '../../service';
import { Router, ActivatedRoute  } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import {AccountService} from "../../service/account.service";
import { Account } from "../../shared/models/account";

@Component({
  selector: 'account-details',
  templateUrl: './account.details.component.html',
  styleUrls: ['./account.details.component.scss']
})
export class AccountDetailsComponent implements OnInit {

  account: Account;
  type: string;
  title: string;

  error: string;

  returnUrl: string;

  constructor(
    private userService: UserService,
    private currencyService: CurrencyService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private accountService: AccountService
  ) { }

  ngOnInit(){
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.type = this.route.snapshot.queryParams['t'] || 'p';
    this.title = this.type == 'p' ? 'Primary account' : 'Saving account';
    this.account = this.route.snapshot.data['account'];
  }



}
