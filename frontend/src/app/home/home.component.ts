import { Component, OnInit } from '@angular/core';
import {
  AccountService,
  ConfigService,
  UserService
} from '../service';
import {Account} from '../shared/models/account';
import { forkJoin } from 'rxjs/observable/forkJoin';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private primaryAccounts: Account[];
  private savingAccounts: Account[];
  constructor(
    private config: ConfigService,
    private accountService: AccountService,
    private userService: UserService
  ) { }

  ngOnInit() {
    forkJoin([this.accountService.getPrimaryAccounts(), this.accountService.getSavingAccounts()]).subscribe(result => {
      this.primaryAccounts = result[0];
      this.savingAccounts = result[1];
    });
  }

}
