import { Component, computed, ElementRef, model, signal, viewChild } from '@angular/core';
import { concat } from 'rxjs';

@Component({
  selector: 'app-parent',
  template: `
    <div class="parent-container">
      <h1 class="title">Signals Demo</h1>
      <div class="input-container">
      Celsius: <input type="text" [(ngModel)]="celsius" placeholder="Enter celsius"/>
      <div class="button-container">
        <button (click)="onToFahrenheit()">Enter</button>
      </div>
      </div>
      <div class="input-container">
      Fahrenheit: <input type="text" [(ngModel)]="fahrenheit" placeholder="Enter fahrenheit" />
      <div class="button-container">
        <button (click)="onToCelsius()">Enter</button>
      </div>
      </div>
      <div class="log-container">
        <span>Log: {{ tempMap() }}</span>
      </div>
      <app-child 
        [celsius]="celsius()"
        [fahrenheit]="fahrenheit()"
        (log)="onLog($event)"
        (clear)="onClear()">
      </app-child>
    </div>
  `,
  styleUrl: './parent.component.scss'
})
export class ParentComponent {
  celsius = model<number>(0);
  fahrenheit = model<number>(32);

  tempMap = signal<string[]>([]);

  onToFahrenheit(){
    this.fahrenheit.set(parseFloat((this.celsius() * 1.8 + 32).toFixed(2)));
  }

  onToCelsius(){
    this.celsius.set(parseFloat((this.fahrenheit() / 1.8 - 32).toFixed(2)));
  }

  onLog(fahrenheit: number){
    this.tempMap.update(temp => [...temp, [this.celsius(), fahrenheit].join(': ')]);
  }

  onClear(){
    this.tempMap.set([]);
    this.celsius.set(0);
  }
}
