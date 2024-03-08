import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessTabComponent } from './business-tab.component';

describe('BusinessTabComponent', () => {
  let component: BusinessTabComponent;
  let fixture: ComponentFixture<BusinessTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessTabComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
