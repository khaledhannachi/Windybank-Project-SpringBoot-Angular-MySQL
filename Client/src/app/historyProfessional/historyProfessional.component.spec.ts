import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryProfessionalComponent } from './historyProfessional.component';

describe('HistoryComponent', () => {
  let component: HistoryProfessionalComponent;
  let fixture: ComponentFixture<HistoryProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
