import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
// material
import {
  MatButtonModule,
  MatMenuModule,
  MatIconModule,
  MatToolbarModule,
  MatTooltipModule,
  MatCardModule,
  MatInputModule,
  MatIconRegistry,
  MatProgressSpinnerModule,
  MatPaginatorModule,
  MatSortModule
} from '@angular/material';
import {MatSelectModule} from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { LoginGuard, GuestGuard, AdminGuard } from './guard';
import { NotFoundComponent } from './not-found';
import { AccountMenuComponent } from './component/header/account-menu/account-menu.component';
import { AccountLogicMenuComponent } from './component/header/account-logic-menu/account-logic-menu.component';
import {
  HeaderComponent
} from './component';

import {
  ApiService,
  AuthService,
  UserService,
  AccountService,
  ConfigService,
  CurrencyService
} from './service';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminComponent } from './admin/admin.component';
import { SignupComponent } from './signup/signup.component';
import {AccountCardComponent} from "./component/account-card/account-card.component";
import {CreateAccountComponent} from "./component/create-account/create.account.component";
import { CurrencyDirective } from "./directives/currency.directive";
import { DepositComponent } from "./component/deposit/deposit.component";
import { CurrencyIconComponent } from "./component/currency-icon/currency.icon.component";
import {AccountDetailsComponent} from "./component/account-details/account.details.component";
import {AccountResolver} from "./resolvers/account.resolver";
import {TransactionTableComponent} from "./component/account-details/transaction-table/transaction.table.component";

export function initUserFactory(userService: UserService) {
    return () => userService.initUser();
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    AccountCardComponent,
    LoginComponent,
    NotFoundComponent,
    AccountMenuComponent,
    AccountLogicMenuComponent,
    ChangePasswordComponent,
    ForbiddenComponent,
    AdminComponent,
    SignupComponent,
    CreateAccountComponent,
    CurrencyDirective,
    AccountLogicMenuComponent,
    DepositComponent,
    CurrencyIconComponent,
    AccountDetailsComponent,
    TransactionTableComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
    MatMenuModule,
    MatTooltipModule,
    MatButtonModule,
    MatIconModule,
    MatSelectModule,
    MatInputModule,
    MatToolbarModule,
    MatCardModule,
    MatProgressSpinnerModule,
    FlexLayoutModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule
  ],
  providers: [
    LoginGuard,
    GuestGuard,
    AdminGuard,
    AccountService,
    AuthService,
    ApiService,
    UserService,
    CurrencyService,
    ConfigService,
    MatIconRegistry,
    AccountResolver,
    {
      'provide': APP_INITIALIZER,
      'useFactory': initUserFactory,
      'deps': [UserService],
      'multi': true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
