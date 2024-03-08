import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivedaccountPersonelComponent } from './activedaccount-personel.component';

describe('ActivedaccountPersonelComponent', () => {
  let component: ActivedaccountPersonelComponent;
  let fixture: ComponentFixture<ActivedaccountPersonelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivedaccountPersonelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivedaccountPersonelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
