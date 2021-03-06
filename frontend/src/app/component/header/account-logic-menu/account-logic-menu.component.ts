import { Component, OnInit, Input } from '@angular/core';
import {
  ConfigService,
  AuthService,
  UserService
} from '../../../service';
import { Router } from '@angular/router';

@Component({
  selector: 'account-logic-menu',
  templateUrl: './account-logic-menu.component.html',
  styleUrls: ['./account-logic-menu.component.scss']
})
export class AccountLogicMenuComponent implements OnInit {

  user: any;

  constructor(
    private config: ConfigService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.user = this.userService.currentUser;
  }

  logout() {
    this.authService.logout().subscribe(res => {
      this.router.navigate(['/login']);
    });
  }
}

@Component({
  selector: 'transmit-menu',
  templateUrl: './transmit-menu.component.html',
  styleUrls: ['./account-logic-menu.component.scss']
})
export class TransmitMenuComponent implements OnInit {

  user: any;

  constructor(
    private config: ConfigService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.user = this.userService.currentUser;
  }

  logout() {
    this.authService.logout().subscribe(res => {
      this.router.navigate(['/login']);
    });
  }
}
