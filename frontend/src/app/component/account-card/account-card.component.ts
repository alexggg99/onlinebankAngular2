import { Component, OnInit } from '@angular/core';
import {
  UserService,
  AuthService
} from '../../service';
import { Router } from '@angular/router';

@Component({
  selector: 'account-card',
  templateUrl: './account-card.component.html',
  styleUrls: ['./account-card.component.scss']
})
export class AccountCardComponent implements OnInit {

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }


}
