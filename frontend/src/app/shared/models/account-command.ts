import {Account} from "./account";

export class AccountCommand {
  accountId: number;
  amount: number;

  constructor(account: Account, amount: number) {
    this.accountId = account.id;
    this.amount = amount;
  }
}
