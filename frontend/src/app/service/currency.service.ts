import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import {Currency} from '../shared/models/currency'

import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CurrencyService {

  private _currency_url = '/api/currency'

  constructor(
    private apiService: ApiService
  ) { }

  getCurrencies(): Observable<Currency[]> {
    return this.apiService.get(this._currency_url);
  }

}
