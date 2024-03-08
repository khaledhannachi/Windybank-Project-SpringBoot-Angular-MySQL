import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryPdfBusinessComponent } from './history-pdfBusiness.component';

describe('HistoryPDFComponent', () => {
  let component: HistoryPdfBusinessComponent;
  let fixture: ComponentFixture<HistoryPdfBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryPdfBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryPdfBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
