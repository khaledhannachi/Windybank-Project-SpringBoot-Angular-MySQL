import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RibPageBusinessComponent } from './rib-pageBusiness.component';

describe('RibPageComponent', () => {
  let component: RibPageBusinessComponent;
  let fixture: ComponentFixture<RibPageBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RibPageBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RibPageBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
