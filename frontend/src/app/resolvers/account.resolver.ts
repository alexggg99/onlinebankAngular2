import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {AccountService} from "../service/account.service";
import {Observable} from "rxjs/Observable";
import {Account} from "../shared/models/account";

@Injectable()
export class AccountResolver implements Resolve<Account> {

  constructor(private accountService: AccountService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Account> | Promise<Account> | Account {
    return this.accountService.getAccount(route.params['id']);
  }

}
