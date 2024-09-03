import { Component, computed, effect, signal } from '@angular/core';

@Component({
  selector: "app-signal-change-detection",
  template: `
  <div class="container">
    <h1 class="title">Signals: Change Detection</h1>
    <div class="input-container">
    <p>{{ message() }}</p>
    <div class="button-container">
    <button (click)="updateMessage()">Update Message (Signal)</button>
    </div>
    </div>
  </div>
`,
  styleUrl: './signal-change-detection.component.scss'
})
export class SignalChangeDetectionComponent {
  private messageSignal = signal('Initial Message');

  message = computed(() => this.messageSignal());

  constructor() {
    effect(() => {
      console.log('Message updated:', this.message());
    });
  }

  updateMessage() {
    setTimeout(() => {
      this.messageSignal.set('Message updated with Signal!');
    }, 1000);
  }
}