import {Account} from "./account";

export class AccountCommand {
  action: string;
  accountId: number;
  amount: number;

  constructor(action: string, account: Account, amount: number) {
    this.action = action;
    this.accountId = account.id;
    this.amount = amount;
  }
}
