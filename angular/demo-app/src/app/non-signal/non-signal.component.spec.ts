import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonSignalComponent } from './non-signal.component';

describe('NonSignalComponent', () => {
  let component: NonSignalComponent;
  let fixture: ComponentFixture<NonSignalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NonSignalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NonSignalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
