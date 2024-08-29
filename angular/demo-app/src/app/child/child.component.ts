import { Component, computed, input, output } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `
  <div class="child-container">
      @if (isExist()) {
        <span>C: {{ celsius() }}</span> <br>
        <span>F: {{ fahrenheit() }}</span> 
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
  celsius = input.required<number>();
  // The computed only re-evaluates if celsius() changes.
  fahrenheit = computed(() => parseFloat((this.celsius() * 1.8 + 32).toFixed(2)));

  isExist = computed(() =>  this.celsius() !== 0);

  log = output<number>();
  clear = output();

  onLog(){
    this.log.emit(this.fahrenheit());
  }

  onClear(){
    this.clear.emit();
  }

}
