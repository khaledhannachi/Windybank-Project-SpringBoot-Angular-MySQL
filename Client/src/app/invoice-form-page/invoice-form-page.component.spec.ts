import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceFormPageComponent } from './invoice-form-page.component';

describe('InvoiceFormPageComponent', () => {
  let component: InvoiceFormPageComponent;
  let fixture: ComponentFixture<InvoiceFormPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoiceFormPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvoiceFormPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
