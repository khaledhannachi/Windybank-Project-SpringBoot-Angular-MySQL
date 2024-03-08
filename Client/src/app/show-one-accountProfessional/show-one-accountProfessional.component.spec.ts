import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowOneAccountProfessionalComponent } from './show-one-accountProfessional.component';

describe('ShowOneAccountComponent', () => {
  let component: ShowOneAccountProfessionalComponent;
  let fixture: ComponentFixture<ShowOneAccountProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowOneAccountProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowOneAccountProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
