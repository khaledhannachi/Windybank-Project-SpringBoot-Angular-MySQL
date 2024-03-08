import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedaccountProfessionalComponent } from './deletedaccount-professional.component';

describe('DeletedaccountProfessionalComponent', () => {
  let component: DeletedaccountProfessionalComponent;
  let fixture: ComponentFixture<DeletedaccountProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletedaccountProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletedaccountProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
