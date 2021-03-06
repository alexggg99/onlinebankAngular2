import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { LoginGuard } from './guard';
import { GuestGuard, AdminGuard } from './guard';
import { NotFoundComponent } from './not-found';
import { ChangePasswordComponent } from './change-password';
import { ForbiddenComponent } from './forbidden';
import { SignupComponent } from './signup';
import { CreateAccountComponent } from './component/create-account/create.account.component';
import { DepositComponent } from "./component/deposit/deposit.component";
import {AccountDetailsComponent} from "./component/account-details/account.details.component";
import {AccountResolver} from "./resolvers/account.resolver";
import {TransmitComponent} from "./component/transmit/transmit.component";

export const routes: Routes = [
  {
    path:'signup',
    component: SignupComponent,
    canActivate: [LoginGuard],
    pathMatch:'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'index',
    component: HomeComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'create',
    component: CreateAccountComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'deposit',
    component: DepositComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'withdraw',
    component: DepositComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'transmit',
    component: TransmitComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'account-details/:id',
    component: AccountDetailsComponent,
    canActivate: [GuestGuard],
    resolve: {
      account: AccountResolver
    }
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '403',
    component: ForbiddenComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
