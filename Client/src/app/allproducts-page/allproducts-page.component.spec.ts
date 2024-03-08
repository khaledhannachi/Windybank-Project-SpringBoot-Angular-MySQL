import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllproductsPageComponent } from './allproducts-page.component';

describe('AllproductsPageComponent', () => {
  let component: AllproductsPageComponent;
  let fixture: ComponentFixture<AllproductsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllproductsPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllproductsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
