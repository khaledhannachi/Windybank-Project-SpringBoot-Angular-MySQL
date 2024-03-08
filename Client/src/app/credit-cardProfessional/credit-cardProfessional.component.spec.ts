import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditCardProfessionalComponent } from './credit-cardProfessional.component';

describe('CreditCardComponent', () => {
  let component: CreditCardProfessionalComponent;
  let fixture: ComponentFixture<CreditCardProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditCardProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreditCardProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
