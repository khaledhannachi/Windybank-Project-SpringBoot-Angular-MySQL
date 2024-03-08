import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountsPageBusinessComponent } from './accounts-pageBusiness.component';

describe('AccountsPageComponent', () => {
  let component: AccountsPageBusinessComponent;
  let fixture: ComponentFixture<AccountsPageBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountsPageBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountsPageBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
