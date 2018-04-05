import {Component, Input, OnInit} from '@angular/core';
import {Currency} from "../../shared/models/currency";

@Component({
  selector: 'currency-icon',
  templateUrl: './currency.icon.component.html',
  styleUrls: ['./currency.icon.component.scss']
})
export class CurrencyIconComponent implements OnInit {

  @Input() value: Currency;

  constructor() { }

  ngOnInit(){
  }


}
