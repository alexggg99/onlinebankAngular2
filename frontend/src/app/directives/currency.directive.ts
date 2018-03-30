import {Directive, ElementRef, Inject, Input, OnInit, Renderer, Renderer2} from "@angular/core";
import {DOCUMENT} from "@angular/common";

@Directive({selector: '[currency]'})
export class CurrencyDirective implements OnInit{

  @Input() currency: any;
  constructor(private el: ElementRef, private renderer: Renderer2, @Inject(DOCUMENT) private document) {}


  ngOnInit(): void {
    this.renderer.insertBefore(this.el.nativeElement, this.createIcon(this.currency), this.el.nativeElement.firstChild);
  }

  createIcon(cur: string) {
    var icon =  this.document.createElement("i");
    if (this.currency == 'EUR') {
      icon.setAttribute("class", "fa fa-euro");
    } if (this.currency == 'USD') {
      icon.setAttribute("class", "fa fa-usd");
    } else {
      icon.setAttribute("class", "fa fa-rub");
    }
    icon.setAttribute("aria-hidden", "true");
    return icon;
  }
}
