import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryBusinesscomponent } from './historyBusinesscomponent';

describe('HistoryComponent', () => {
  let component: HistoryBusinesscomponent;
  let fixture: ComponentFixture<HistoryBusinesscomponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryBusinesscomponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryBusinesscomponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
