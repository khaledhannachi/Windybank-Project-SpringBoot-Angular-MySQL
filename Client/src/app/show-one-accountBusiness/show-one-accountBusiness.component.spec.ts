import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowOneAccountBusinessComponent } from './show-one-accountBusiness.component';

describe('ShowOneAccountComponent', () => {
  let component: ShowOneAccountBusinessComponent;
  let fixture: ComponentFixture<ShowOneAccountBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowOneAccountBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowOneAccountBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
