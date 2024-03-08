import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuspentedaccountBusinessComponent } from './suspentedaccount-business.component';

describe('SuspentedaccountBusinessComponent', () => {
  let component: SuspentedaccountBusinessComponent;
  let fixture: ComponentFixture<SuspentedaccountBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuspentedaccountBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuspentedaccountBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
