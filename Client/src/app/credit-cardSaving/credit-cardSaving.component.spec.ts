import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditCardSavingComponent } from './credit-cardSaving.component';

describe('CreditCardComponent', () => {
  let component: CreditCardSavingComponent;
  let fixture: ComponentFixture<CreditCardSavingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditCardSavingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreditCardSavingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
