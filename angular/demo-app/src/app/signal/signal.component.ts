import { Component, computed, signal } from '@angular/core';

@Component({
  selector: "app-signal",
  template: `
  <div class="container">
    <h1 class="title">Signals Change Detection</h1>
    <div class="input-container">
    Count: {{ count() }} <br>
    Double Count: {{ doubleCount() }} <br>
    Double Double Count: {{ doubleDoubleCount() }}
    <div class="button-container">
    <button (click) = "changeCount()"> Change </button>
    </div>
    </div>
  </div>
`,
  styleUrl: './signal.component.scss'
})
export class SignalComponent {
    count = signal(0);
    doubleCount = computed(() => this.count() * 2); //Reactive and Declarative style of Code
    doubleDoubleCount = computed(() => this.doubleCount() * 2);
    
    changeCount(){
        this.count.set(5);
    }
}
