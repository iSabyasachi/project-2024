import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZoneChangeDetectionComponent } from './zone-change-detection.component';

describe('ZoneChangeDetectionComponent', () => {
  let component: ZoneChangeDetectionComponent;
  let fixture: ComponentFixture<ZoneChangeDetectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ZoneChangeDetectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZoneChangeDetectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
