import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedaccountbusinessComponent } from './deletedaccountbusiness.component';

describe('DeletedaccountbusinessComponent', () => {
  let component: DeletedaccountbusinessComponent;
  let fixture: ComponentFixture<DeletedaccountbusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletedaccountbusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletedaccountbusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
