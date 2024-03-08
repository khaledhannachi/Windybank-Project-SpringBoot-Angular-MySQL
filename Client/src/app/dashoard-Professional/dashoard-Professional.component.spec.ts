import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashoardProfessionalComponent } from './dashoard-Professional.component';

describe('DashoardPersonalComponent', () => {
  let component: DashoardProfessionalComponent;
  let fixture: ComponentFixture<DashoardProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashoardProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashoardProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
