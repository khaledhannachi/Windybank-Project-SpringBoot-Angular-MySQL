import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginBusinessesComponent } from './login-professionals.component';

describe('LoginBusinessesComponent', () => {
  let component: LoginBusinessesComponent;
  let fixture: ComponentFixture<LoginBusinessesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginBusinessesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginBusinessesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
