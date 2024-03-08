import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RibPageComponent } from './rib-page.component';

describe('RibPageComponent', () => {
  let component: RibPageComponent;
  let fixture: ComponentFixture<RibPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RibPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RibPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
