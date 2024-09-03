import { Component, computed, inject, model, effect, ModelSignal, signal } from '@angular/core';
import { Currency, CurrencyInfo, CurrencyService } from './currency.service';

@Component({
  selector: "app-currency",
  template: `
  <div class="container">
    <h1 class="title">Signals: Currency Converter</h1>
    <div class="input-container">
      <input type="text" [(ngModel)]="firstInput" (keyup) = "onToFirstInput()"/>
      <select [value]="firstCurrency()" (change)="onFirstCurrencyChanges($event)">
        <option value="USD">United States Dollar</option>
        <option value="EUR">Euro</option>
        <option value="GBP">Pound sterling</option>
      </select>
    </div>
    <div class="input-container">
      <input type="text" [(ngModel)]="secondInput" (keyup) = "onToSecondInput()"/>
      <select [value]="secondCurrency()" (change)="onSecondCurrencyChanges($event)">
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
  //User Inputs
  firstInput = model<number>(1);
  secondInput = model<number>(1.14);
  firstCurrency = signal<Currency>('USD');
  secondCurrency = signal<Currency>('EUR');

  #currencyService = inject(CurrencyService);
  #firstCurrencyInfo = this.#currencyService.getFirstCurrencyInfo();
  #secondCurrencyInfo = this.#currencyService.getSecondCurrencyInfo();

  constructor(){
    effect(() => {
      console.log(`firstInput is ${this.firstInput()}`);
      console.log(`secondInput is ${this.secondInput()}`);
      console.log(`firstCurrency is ${this.firstCurrency()}`);
      console.log(`secondCurrency is ${this.secondCurrency()}`);
      console.log(`firstCurrencyInfo is ${JSON.stringify(this.#firstCurrencyInfo())}`);
      console.log(`secondCurrencyInfo is ${JSON.stringify(this.#secondCurrencyInfo())}`);
    });
  }

  private updateInput(input: ModelSignal<number>, value: number){
    input.set(value);
  }

  private convertCurrency(value: number, firstRate: number, secondRate: number): number{
    return Number(((value * firstRate) / secondRate).toFixed(2));
  }

  onToFirstInput(){
    this.updateInput(this.secondInput, 
      this.convertCurrency(this.firstInput(), this.#secondCurrencyInfo().exchangeRate,
      this.#firstCurrencyInfo().exchangeRate
    ));
  }

  onToSecondInput(){
    this.updateInput(this.firstInput, 
      this.convertCurrency(this.secondInput(), this.#firstCurrencyInfo().exchangeRate,
      this.#secondCurrencyInfo().exchangeRate
    ));
  }

  onFirstCurrencyChanges(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    this.#currencyService.setFirstCurrency(<Currency>selectElement.value);

    this.updateInput(this.secondInput, 
      this.convertCurrency(this.firstInput(), this.#secondCurrencyInfo().exchangeRate,
      this.#firstCurrencyInfo().exchangeRate
    ));
  }

  onSecondCurrencyChanges(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    this.#currencyService.setSecondCurrency(<Currency>selectElement.value);

    this.updateInput(this.firstInput, 
      this.convertCurrency(this.secondInput(), this.#firstCurrencyInfo().exchangeRate,
      this.#secondCurrencyInfo().exchangeRate
    ));
  }
}
