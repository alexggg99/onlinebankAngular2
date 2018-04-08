import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Account} from "../../../shared/models/account";
import { AccountService } from "../../../service/account.service";
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';

import {merge} from 'rxjs/observable/merge';
import {of as observableOf} from 'rxjs/observable/of';
import {catchError} from 'rxjs/operators/catchError';
import {map} from 'rxjs/operators/map';
import {startWith} from 'rxjs/operators/startWith';
import {switchMap} from 'rxjs/operators/switchMap';
import { forkJoin } from 'rxjs/observable/forkJoin';

@Component({
  selector: 'transaction-table',
  templateUrl: './transaction.table.component.html',
  styleUrls: ['./transaction.table.component.scss']
})
export class TransactionTableComponent implements OnInit {
  @Input() account: Account;

  displayedColumns = ['date', 'type', 'status', 'amount'];
  dataSource = new MatTableDataSource();

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private accountService: AccountService) { }

  ngOnInit(){

    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return forkJoin(
            this.accountService.getTransactions(this.account, this.sort.active || 'date', this.sort.direction || 'desc', this.paginator.pageIndex),
            this.accountService.getTransactionsSize(this.account)
          );
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.isRateLimitReached = false;
          this.resultsLength = data[1];

          return data[0];
        }),
        catchError(() => {
          this.isLoadingResults = false;
          // Catch if the GitHub API has reached its rate limit. Return empty data.
          this.isRateLimitReached = true;
          return observableOf([]);
        })
      ).subscribe(data => {
        console.log(data);
        this.dataSource.data = data
      });

  }



}
