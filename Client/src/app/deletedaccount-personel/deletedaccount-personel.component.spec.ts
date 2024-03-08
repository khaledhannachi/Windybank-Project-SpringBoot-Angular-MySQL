import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedaccountPersonelComponent } from './deletedaccount-personel.component';

describe('DeletedaccountPersonelComponent', () => {
  let component: DeletedaccountPersonelComponent;
  let fixture: ComponentFixture<DeletedaccountPersonelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletedaccountPersonelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletedaccountPersonelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
