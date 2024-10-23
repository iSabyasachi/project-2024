import { Component, computed, inject, model, effect, ModelSignal, signal } from '@angular/core';
import { Currency, CurrencyInfo, CurrencyService } from './currency.service';

@Component({
  selector: "app-currency",
  template: `
  <div class="container">
    <h1 class="title">toSignal(): Currency Converter</h1>
    <div class="input-container">
      <input type="text" [(ngModel)]="firstInput" (keyup) = "onFirstInputChange()"/>
      <select [ngModel]="firstCurrencyInfo().currency" (ngModelChange)="onFirstCurrencyChange($event)">
        <option value="USD">United States Dollar</option>
        <option value="EUR">Euro</option>
        <option value="GBP">Pound sterling</option>
      </select>
    </div>
    <div class="input-container">
      <input type="text" [(ngModel)]="secondInput" (keyup) = "onSecondInputChange()"/>
      <select [ngModel]="secondCurrencyInfo().currency" (ngModelChange)="onSecondCurrencyChange($event)">
        <option value="USD">United States Dollar</option>
        <option value="EUR">Euro</option>
        <option value="GBP">Pound sterling</option>
      </select>
    </div>
    <div>
      <span class="currency-display">
          {{ firstInput() }} {{ firstCurrency() }} is {{ secondInput() }} {{ secondCurrency() }}
      </span>
      <span class="currency-display-note">Note: EUR:1.14, GBP:1.31, USD:1</span>
    </div>
  </div>
`,
  styleUrl: './currency.component.scss'
})
export class CurrencyComponent {
  firstInput = model<number>(1);
  secondInput = model<number>(1.14);
  firstCurrency = signal<Currency>('USD');
  secondCurrency = signal<Currency>('EUR');

  #currencyService = inject(CurrencyService);
  readonly firstCurrencyInfo = this.#currencyService.firstCurrencyInfo;
  readonly secondCurrencyInfo = this.#currencyService.secondCurrencyInfo;

  constructor(){
    effect(() => {
      console.log(`firstInput is ${this.firstInput()}`);
      console.log(`secondInput is ${this.secondInput()}`);
      console.log(`firstCurrency is ${this.firstCurrency()}`);
      console.log(`secondCurrency is ${this.secondCurrency()}`);
      console.log(`firstCurrencyInfo is ${JSON.stringify(this.firstCurrencyInfo())}`);
      console.log(`secondCurrencyInfo is ${JSON.stringify(this.secondCurrencyInfo())}`);
    });
  }

  private convertCurrency(value: number, fromRate: number, toRate: number): number {
    return Number(((value * toRate) / fromRate).toFixed(2));
  }

  private updateConversion() {
    this.secondInput.set(this.convertCurrency(this.firstInput(), this.firstCurrencyInfo().exchangeRate, this.secondCurrencyInfo().exchangeRate));
  }

  onFirstInputChange(){
    this.updateConversion();
  }

  onSecondInputChange() {
    this.firstInput.set(this.convertCurrency(this.secondInput(), this.secondCurrencyInfo().exchangeRate, this.firstCurrencyInfo().exchangeRate));
  }

  onFirstCurrencyChange(currency: Currency) {
    this.#currencyService.setFirstCurrency(currency);
    this.updateConversion();
  }

  onSecondCurrencyChange(currency: Currency) {
    this.#currencyService.setSecondCurrency(currency);
    this.updateConversion();
  }
}
