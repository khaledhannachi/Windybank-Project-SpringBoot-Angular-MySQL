import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarProfessionalComponent } from './sidebar-Professional.component';

describe('SidebarPersonalComponent', () => {
  let component: SidebarProfessionalComponent;
  let fixture: ComponentFixture<SidebarProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarProfessionalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
