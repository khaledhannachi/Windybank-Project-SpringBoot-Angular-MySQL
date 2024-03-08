import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestMoneyBusinessComponent } from './request-moneyBusiness.component';

describe('RequestMoneyComponent', () => {
  let component: RequestMoneyBusinessComponent;
  let fixture: ComponentFixture<RequestMoneyBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestMoneyBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestMoneyBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
