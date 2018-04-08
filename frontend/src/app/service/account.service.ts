import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';
import {Account} from '../shared/models/account';

import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {AccountCommand} from "../shared/models/account-command";
import {Currency} from "../shared/models/currency";
import {Transaction} from "../shared/models/transaction";

@Injectable()
export class AccountService {

  private _account_url = '/api/account';
  private _transaction_url = '/api/transaction';

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

  getAccount(accountId: number): Observable<Account> {
    return this.apiService.get(this._account_url + '/' + accountId);
  }

  createPrimaryAccount(currency: Currency) : Observable<Account> {
    return this.apiService.post(this._account_url + '/primary', currency);
  }

  deposit(accountCommand: AccountCommand): Observable<Account> {
    return this.apiService.post(this._account_url, accountCommand);
  }

  getTransactions(account: Account, sort: string, order: string, page: number): Observable<Transaction[]> {
    return this.apiService.get(this._transaction_url + '/' + account.id, {sort: sort, order: order, page: page});
  }

  getTransactionsSize(account: Account): Observable<number> {
    return this.apiService.get(this._transaction_url + '/size/' + account.id);
  }

}
