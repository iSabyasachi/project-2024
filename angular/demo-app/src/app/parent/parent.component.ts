import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-parent',
  template: `
    <div class="parent-container">
      <h1 class="title">Signals Demo</h1>
      <div class="input-container">
        <input type="text" [(ngModel)]="inputNum" placeholder="Enter a number" />
        <button (click)="onSubmit()">Submit</button>
      </div>
      <div class="log-container">
        <span>Log: {{ numArray() }}</span>
      </div>
      <app-child 
        [num]="num()"
        (log)="onLog($event)"
        (clear)="onClear()">
      </app-child>
    </div>
  `,
  styleUrl: './parent.component.scss'
})
export class ParentComponent {
  num = signal<number>(0);
  numArray = signal<number[]>([]);
  inputNum: number = 0;
  
  onSubmit(){
    this.num.set(this.inputNum);
    this.inputNum = 0;
  }

  onLog(num: number){
    this.numArray.update(array => [...array, num]);
  }

  onClear(){
    this.numArray.set([]);
    this.num.set(0);
  }
}
