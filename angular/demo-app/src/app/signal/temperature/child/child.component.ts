import { Component, computed, input, model, OnInit, output } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `
  <div class="child-container">
      @if (isExist()) {
        Formula <span class="temperature-display">
          ({{ celsius() }}&deg;C * 9/5) + 32 = {{ fahrenheit() }}&deg;F
        </span>
      } 
      <div class="button-container">
        <button (click)="onLog()">Log</button>
        <button (click)="onClear()">Clear</button>
      </div>
    </div>
  `,
  styleUrl: './child.component.scss'
})
export class ChildComponent{
  celsius = input.required<number>();
  fahrenheit = computed(() => parseFloat((this.celsius() * 1.8 + 32).toFixed(2)));
  isExist = computed(() =>  this.celsius() !== undefined);
  
  log = output<number>();
  clear = output();

  onLog(){
    this.log.emit(this.fahrenheit());
  }

  onClear(){
    this.clear.emit();
  }

}
