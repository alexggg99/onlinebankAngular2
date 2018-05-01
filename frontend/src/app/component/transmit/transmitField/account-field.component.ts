import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Account} from "../../../shared/models/account";
import {FormGroup} from "@angular/forms";
import {AccountService} from "../../../service/account.service";

@Component({
  selector: 'account-field',
  templateUrl: './account-field.component.html',
  styleUrls: ['./account-field.component.scss']
})
export class AccountFieldComponent implements OnChanges {

  @Input() accountFrom: Account;
  @Input() accountTo: Account;
  @Input() form: FormGroup;
  accountsTo: Account[];

  constructor(
    private accountService: AccountService
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.accountService.getAllAccounts().map(accounts => {
      return accounts.filter(item => this.form.controls['accountFrom'].value.id != item.id);
    }).subscribe(
      res => {
        this.accountsTo = res;
      }
    );
  }

}
