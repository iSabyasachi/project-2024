import { Component } from '@angular/core';

@Component({
  selector: "app-non-signal",
  template: `
  <div class="container">
    <h1 class="title">Non-Signals Change Detection</h1>
    <div class="input-container">
    Count: {{ count }} <br>
    Double Count: {{ doubleCount }} <br>
    Double Double Count: {{ doubleDoubleCount }}
    <div class="button-container">
    <button (click) = "changeCount()"> Change </button>
    </div>
    </div>
  </div>
`,
  styleUrl: './non-signal.component.scss'
})
export class NonSignalComponent {
    count = 0;
    doubleCount = this.count * 2; //Still 0
    doubleDoubleCount = this.doubleCount * 2; //Still 0

    changeCount(){
        this.count = 5;

        this.doubleCount = this.count * 2;
        this.doubleDoubleCount = this.doubleCount * 2;
    }
}
