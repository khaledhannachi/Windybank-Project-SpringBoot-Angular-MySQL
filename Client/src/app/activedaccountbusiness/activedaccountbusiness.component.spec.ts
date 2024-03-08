import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivedaccountbusinessComponent } from './activedaccountbusiness.component';

describe('ActivedaccountbusinessComponent', () => {
  let component: ActivedaccountbusinessComponent;
  let fixture: ComponentFixture<ActivedaccountbusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivedaccountbusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivedaccountbusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
