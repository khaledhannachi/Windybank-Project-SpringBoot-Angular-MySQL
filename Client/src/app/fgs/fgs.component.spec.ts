import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FgsComponent } from './fgs.component';

describe('FgsComponent', () => {
  let component: FgsComponent;
  let fixture: ComponentFixture<FgsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FgsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FgsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
