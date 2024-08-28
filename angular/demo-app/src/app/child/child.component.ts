import { Component, computed, input, output } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `
  <div class="child-container">
      @if (isExist()) {
        <span>Square of {{ num() }} = {{ squareOfNum() }}</span> 
      } 
      <div class="button-container">
        <button (click)="onLog()">Log</button>
        <button (click)="onClear()">Clear</button>
      </div>
    </div>
  `,
  styleUrl: './child.component.scss'
})
export class ChildComponent {
  num = input.required<number>();
  isExist = computed(() =>  this.num() !== 0);
  squareOfNum = computed(() => this.num() * this.num());
  log = output<number>();
  clear = output();

  onLog(){
    this.log.emit(this.squareOfNum());
  }
  onClear(){
    this.clear.emit();
  }
}
