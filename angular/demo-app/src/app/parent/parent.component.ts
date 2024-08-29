import { Component, computed, ElementRef, model, signal, viewChild } from '@angular/core';
import { concat } from 'rxjs';

@Component({
  selector: 'app-parent',
  template: `
    <div class="parent-container">
      <h1 class="title">Signals Demo</h1>
      <div class="input-container">
      Celsius: <input type="text" [(ngModel)]="celsius" placeholder="Enter celsius"/>
      </div>
      <div class="input-container">
      Fahrenheit: <input type="text" [(ngModel)]="fahrenheit" placeholder="Enter fahrenheit" />
      </div>
      <div class="log-container">
        <span>Log: {{ tempMap() }}</span>
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
  celsius = model<number>(0);
  fahrenheit = model<number>(32);

  tempMap = signal<string[]>([]);

  onLog(fahrenheit: number){
    this.fahrenheit.set(fahrenheit);
    this.tempMap.update(temp => [...temp, [this.celsius(), fahrenheit].join(': ')]);
  }

  onClear(){
    this.tempMap.set([]);
    this.celsius.set(0);
    this.fahrenheit.set(32);
  }
}
