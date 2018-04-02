import {Currency} from './currency'

export interface Account {
  id: number;
  accountNumber: number;
  accountBalance: number;
  currency: Currency
}
