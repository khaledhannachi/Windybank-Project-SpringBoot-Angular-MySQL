import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditCardBusinessComponent } from './credit-cardBusiness.component';

describe('CreditCardComponent', () => {
  let component: CreditCardBusinessComponent;
  let fixture: ComponentFixture<CreditCardBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditCardBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreditCardBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
