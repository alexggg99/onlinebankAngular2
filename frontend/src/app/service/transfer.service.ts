import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import {Account} from '../shared/models/account';

import 'rxjs/add/operator/map';

@Injectable()
export class TransferService {

  private _transfer_url = '/api/transfer'

  constructor(
    private apiService: ApiService
  ) { }

  transfer(accountFrom: Account, accountTo: Account, amount: number) {
    return this.apiService.post(this._transfer_url + '/betweenAccounts', {
      accountIdFrom: accountFrom.id,
      accountIdTo: accountTo.id,
      amount: amount
    });
  }

}
