import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuspentedaccountProfessionalComponent } from './suspentedaccount-professional.component';

describe('SuspentedaccountProfessionalComponent', () => {
  let component: SuspentedaccountProfessionalComponent;
  let fixture: ComponentFixture<SuspentedaccountProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuspentedaccountProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuspentedaccountProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
