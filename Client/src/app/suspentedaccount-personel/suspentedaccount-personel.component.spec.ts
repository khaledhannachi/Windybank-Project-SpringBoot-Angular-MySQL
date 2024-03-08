import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuspentedaccountPersonelComponent } from './suspentedaccount-personel.component';

describe('SuspentedaccountPersonelComponent', () => {
  let component: SuspentedaccountPersonelComponent;
  let fixture: ComponentFixture<SuspentedaccountPersonelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuspentedaccountPersonelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuspentedaccountPersonelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
