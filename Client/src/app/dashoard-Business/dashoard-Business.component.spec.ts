import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashoardBusinessComponent } from './dashoard-Business.component';

describe('DashoardPersonalComponent', () => {
  let component: DashoardBusinessComponent;
  let fixture: ComponentFixture<DashoardBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashoardBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashoardBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
