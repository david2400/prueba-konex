import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationAddComponent } from './medication-add.component';

describe('MedicationAddComponent', () => {
  let component: MedicationAddComponent;
  let fixture: ComponentFixture<MedicationAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicationAddComponent]
    });
    fixture = TestBed.createComponent(MedicationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
