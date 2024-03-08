import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestMoneyProfessionalComponent } from './request-moneyProfessional.component';

describe('RequestMoneyComponent', () => {
  let component: RequestMoneyProfessionalComponent;
  let fixture: ComponentFixture<RequestMoneyProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestMoneyProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestMoneyProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
