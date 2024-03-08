import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingAcountPersonelComponent } from './pending-acount-personel.component';

describe('PendingAcountPersonelComponent', () => {
  let component: PendingAcountPersonelComponent;
  let fixture: ComponentFixture<PendingAcountPersonelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingAcountPersonelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PendingAcountPersonelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
