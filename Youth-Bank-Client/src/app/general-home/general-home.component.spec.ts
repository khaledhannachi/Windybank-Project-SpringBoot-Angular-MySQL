import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralHomeComponent } from './general-home.component';

describe('GeneralHomeComponent', () => {
  let component: GeneralHomeComponent;
  let fixture: ComponentFixture<GeneralHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GeneralHomeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GeneralHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
