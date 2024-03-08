import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowOneAccountComponent } from './show-one-account.component';

describe('ShowOneAccountComponent', () => {
  let component: ShowOneAccountComponent;
  let fixture: ComponentFixture<ShowOneAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowOneAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowOneAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
