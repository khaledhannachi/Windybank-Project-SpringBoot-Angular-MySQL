import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-sidebar-Business',
  templateUrl: './sidebar-Business.component.html',
  styleUrls: ['./sidebar-Business.component.css']
})
export class SidebarBusinessComponent implements OnInit{
  public loggedInUser: LoggedInUserInfo | null = null; // Initialize to null

  private loggedInUserSubscription: Subscription | null = null;
  constructor( private authService:AuthService,private route:Router) {
  }
  ngOnInit(): void {

    this.loggedInUserSubscription = this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);
      }
    );
  }

  logout(){this.authService.logout().subscribe({
    next: data => {
      this.route.navigateByUrl("/home")
    },
    error: error => {
      console.error('logout:', error);
    }
  });}
}
