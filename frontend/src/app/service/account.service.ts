import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';
import {Account} from '../shared/models/account';

import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {AccountCommand} from "../shared/models/account-command";

@Injectable()
export class AccountService {

  private _account_url = '/api/account'

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getAllAccounts() : Observable<Account[]> {
    return this.apiService.get(this._account_url);
  }

  getPrimaryAccounts(): Observable<Account[]> {
    return this.apiService.get(this._account_url + '/primary');
  }

  getSavingAccounts(): Observable<Account[]> {
    return this.apiService.get(this._account_url + '/saving');
  }

  getAccount(accountId: number) {
    return this.apiService.get(this._account_url + '/' + accountId);
  }

  deposit(accountCommand: AccountCommand): Observable<Account> {
    return this.apiService.post(this._account_url, accountCommand);
  }

}
