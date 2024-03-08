import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalAdminComponent } from './professionalAdmin.component';

describe('ProfessionalComponent', () => {
  let component: ProfessionalAdminComponent;
  let fixture: ComponentFixture<ProfessionalAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessionalAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
