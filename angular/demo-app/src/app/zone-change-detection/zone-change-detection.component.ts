import { ChangeDetectorRef, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-zone-change-detection',
  template: `
    <p>{{ message }}</p>
    <button (click)="updateMessage()">Update Message (Zone.js)</button>
  `,
})
export class ZoneChangeDetectionComponent implements OnInit {
  message: string = 'Initial Message';

  constructor(private cd: ChangeDetectorRef) {}

  ngOnInit(): void {}

  updateMessage() {
    setTimeout(() => {
      this.message = 'Message updated with Zone.js!';
    }, 1000);
  }
}