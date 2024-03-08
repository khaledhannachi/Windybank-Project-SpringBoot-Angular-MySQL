import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryPdfProfessionalComponent } from './history-pdfProfessional.component';

describe('HistoryPDFComponent', () => {
  let component: HistoryPdfProfessionalComponent;
  let fixture: ComponentFixture<HistoryPdfProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryPdfProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryPdfProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
