import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonelAdminComponent } from './personelAdmin.component';

describe('PersonelComponent', () => {
  let component: PersonelAdminComponent;
  let fixture: ComponentFixture<PersonelAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonelAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonelAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
