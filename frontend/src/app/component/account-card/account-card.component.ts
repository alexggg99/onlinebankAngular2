import {Component, Input, OnInit} from '@angular/core';
import {
  UserService,
  AuthService
} from '../../service';
import { Router } from '@angular/router';
import {Account} from "../../shared/models/account";

@Component({
  selector: 'account-card',
  templateUrl: './account-card.component.html',
  styleUrls: ['./account-card.component.scss']
})
export class AccountCardComponent implements OnInit {

  @Input() account: Account;
  @Input() type: string

  private title: string;
  private currency: any;
  private balance: number;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.type && this.type.toLowerCase() === 'p') {
      this.title = "Primary account";
    } else {
      this.title = "Saving account";
    }
  }

}
