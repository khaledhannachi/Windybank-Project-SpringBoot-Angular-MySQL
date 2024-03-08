import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryPDFComponent } from './history-pdf.component';

describe('HistoryPDFComponent', () => {
  let component: HistoryPDFComponent;
  let fixture: ComponentFixture<HistoryPDFComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryPDFComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryPDFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
