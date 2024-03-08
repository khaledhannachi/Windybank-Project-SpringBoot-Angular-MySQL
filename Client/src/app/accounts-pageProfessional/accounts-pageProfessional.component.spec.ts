import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountsPageProfessionalComponent } from './accounts-pageProfessional.component';

describe('AccountsPageComponent', () => {
  let component: AccountsPageProfessionalComponent;
  let fixture: ComponentFixture<AccountsPageProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountsPageProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountsPageProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
