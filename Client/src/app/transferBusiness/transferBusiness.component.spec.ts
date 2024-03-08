import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferBusinessComponent } from './transferBusiness.component';

describe('TransferComponent', () => {
  let component: TransferBusinessComponent;
  let fixture: ComponentFixture<TransferBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransferBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
