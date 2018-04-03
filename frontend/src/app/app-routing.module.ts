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
import { DepositComponent } from "./component/deposit/deposit.component";

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
    path: 'create-account',
    component: HomeComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'deposit-account',
    component: DepositComponent,
    canActivate: [GuestGuard]
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
