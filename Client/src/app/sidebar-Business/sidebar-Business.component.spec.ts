import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarBusinessComponent } from './sidebar-Business.component';

describe('SidebarPersonalComponent', () => {
  let component: SidebarBusinessComponent;
  let fixture: ComponentFixture<SidebarBusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarBusinessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
