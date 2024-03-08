import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessToolsComponent } from './business-tools.component';

describe('BusinessToolsComponent', () => {
  let component: BusinessToolsComponent;
  let fixture: ComponentFixture<BusinessToolsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessToolsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessToolsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
