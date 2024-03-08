import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConntactComponent } from './conntact.component';

describe('ConntactComponent', () => {
  let component: ConntactComponent;
  let fixture: ComponentFixture<ConntactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConntactComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConntactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
