import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashoardPersonalComponent } from './dashoard-personal.component';

describe('DashoardPersonalComponent', () => {
  let component: DashoardPersonalComponent;
  let fixture: ComponentFixture<DashoardPersonalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashoardPersonalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashoardPersonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
