import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RibPageProfessionalComponent } from './rib-pageProfessional.component';

describe('RibPageComponent', () => {
  let component: RibPageProfessionalComponent;
  let fixture: ComponentFixture<RibPageProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RibPageProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RibPageProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
