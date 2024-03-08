import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivedaccountProfessionalComponent } from './activedaccount-professional.component';

describe('ActivedaccountProfessionalComponent', () => {
  let component: ActivedaccountProfessionalComponent;
  let fixture: ComponentFixture<ActivedaccountProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivedaccountProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivedaccountProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
