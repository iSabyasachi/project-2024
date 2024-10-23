import { Component, computed, ElementRef, model, signal, viewChild } from '@angular/core';
import { concat } from 'rxjs';

@Component({
  selector: 'app-parent',
  template: `
    <div class="parent-container">
      <h1 class="title">input(): Temperature Converter</h1>
      <div class="input-container">
      Celsius: <input type="text" [(ngModel)]="celsius" placeholder="Enter celsius"
      (keyup) = "onToFahrenheit()"/>
      </div>
      <div class="input-container">
      Fahrenheit: <input type="text" [(ngModel)]="fahrenheit" placeholder="Enter fahrenheit" 
      (keyup) = "onToCelsius()"/>
      </div>
      <div class="log-container">
        <span>Log: {{ cache() }}</span>
      </div>
      <app-child 
        [celsius]="celsius()"
        (log)="onLog($event)"
        (clear)="onClear()">
      </app-child>
    </div>
  `,
  styleUrl: './parent.component.scss'
})
export class ParentComponent {
  /**
   * https://angular.dev/guide/signals/inputs
   * https://angular.dev/guide/signals/model
   * 
   * While standard inputs are read-only, you can write directly to model inputs.
   * It defines both an input and an output. It supports two way binding.
   */
  celsius = model<number>(0);
  fahrenheit = model<number>(32);

  cache = signal<string[]>([]);

  onToFahrenheit(){
    this.fahrenheit.set(parseFloat((this.celsius() * 1.8 + 32).toFixed(2)));
  }

  onToCelsius(){
    this.celsius.set(parseFloat(((this.fahrenheit() - 32) / 1.8).toFixed(2)));
  }

  onLog(fahrenheit: number){
    this.cache.update(temp => [...temp, [this.celsius(), fahrenheit].join(': ')]);
  }

  onClear(){
    this.cache.set([]);
    this.celsius.set(0);
    this.fahrenheit.set(32);
  }
}
