import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferProfessionalComponent } from './transferProfessional.component';

describe('TransferComponent', () => {
  let component: TransferProfessionalComponent;
  let fixture: ComponentFixture<TransferProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransferProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
