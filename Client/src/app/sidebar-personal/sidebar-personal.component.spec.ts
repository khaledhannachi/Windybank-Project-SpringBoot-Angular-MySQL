import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarPersonalComponent } from './sidebar-personal.component';

describe('SidebarPersonalComponent', () => {
  let component: SidebarPersonalComponent;
  let fixture: ComponentFixture<SidebarPersonalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarPersonalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarPersonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
