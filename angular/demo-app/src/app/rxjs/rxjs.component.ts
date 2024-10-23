import { Component } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';

@Component({
  selector: "app-rxjs",
  template: `
  <div class="container">
    <h1 class="title">RxJs Change Detection</h1>
    <div class="input-container">
    Count: {{ count$ | async }} <br>
    Double Count: {{ doubleCount$ | async }} <br>
    Double Double Count: {{ doubleDoubleCount$ | async }}
    <div class="button-container">
    <button (click) = "changeCount()"> Change </button>
    </div>
    </div>
  </div>
`,
styleUrls: ['./rxjs.component.scss']
})
export class RxjsComponent {
  count$ = new BehaviorSubject(0);
  doubleCount$ = this.count$.pipe(map((count) => count * 2));
  doubleDoubleCount$ = this.doubleCount$.pipe(map((doubleCount) => doubleCount * 2));

  changeCount(){
    this.count$.next(this.count$.value + 5); // increments the current count value by 5
  }
}
