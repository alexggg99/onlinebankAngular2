import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Account} from "../../shared/models/account";
import {CurrencyService} from "../../service/currency.service";

@Component({
  selector: 'amount-field',
  templateUrl: './amount-field.component.html',
  styleUrls: ['./amount-field.component.scss']
})
export class AmountFieldComponent implements OnInit, OnChanges {

  @Input() accountFrom: Account;
  @Input() accountTo: Account;
  @Input() form: any;

  private title: string;
  private currency: any;
  private icon: string;
  private isExchange: boolean = false;
  private exchange_rate: number;

  constructor(private currencyService: CurrencyService) { }

  ngOnInit() {
    if (this.accountTo && this.accountFrom) {
      if (this.accountTo.currency.name == this.accountFrom.currency.name) {
        this.title = this.accountTo.currency.name;
        this.currency = this.accountTo;
        this.exchange_rate = null;
        this.isExchange = false;
      } else {
        this.title = this.accountFrom.currency.name;
        this.currency = this.accountFrom;
        let amount = this.form.get('amount').value;
        if (amount) {
          this.isExchange = true;
          this.currencyService.getExchageRate(this.accountFrom.currency, this.accountTo.currency, this.form.get('amount').value).subscribe(res => {
            this.exchange_rate =  res;
          });
        }
      }
    }
    this.initIcon();
  }

  initIcon() {
    if (this.title  == 'EUR') {
      this.icon = "fa fa-euro";
    } else if (this.title  == 'USD') {
      this.icon = "fa fa-usd";
    } else {
      this.icon = "fa fa-rub";
    }
  }


  ngOnChanges(changes: SimpleChanges): void {
    if (this.form.get('amount').value == null) {
      this.exchange_rate = null;
    }
    this.ngOnInit();
  }

}
