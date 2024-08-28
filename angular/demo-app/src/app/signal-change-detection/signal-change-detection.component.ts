import { Component, computed, effect, signal } from '@angular/core';

@Component({
  selector: 'app-signal-change-detection',
  template: `
    <p>{{ message() }}</p>
    <button (click)="updateMessage()">Update Message (Signal)</button>
  `,
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