import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignalChangeDetectionComponent } from './signal-change-detection.component';

describe('SignalChangeDetectionComponent', () => {
  let component: SignalChangeDetectionComponent;
  let fixture: ComponentFixture<SignalChangeDetectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignalChangeDetectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignalChangeDetectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
