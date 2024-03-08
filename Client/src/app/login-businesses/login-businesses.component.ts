import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";
import {jwtDecode} from "jwt-decode";
import { LoggedInUserInfo } from "../services/logged-in-user-info";


@Component({
  selector: 'app-login-businesses',
  templateUrl: './login-businesses.component.html',
  styleUrls: ['./login-businesses.component.css']
})
export class LoginBusinessesComponent implements OnInit {
  formLogin!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private route: Router) {}

  ngOnInit() {
    // Initialize the form with email and password fields
    this.formLogin = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // Handle login button click
  handleLogin() {
    let email = this.formLogin.value.email;
    let pwd = this.formLogin.value.password;

    // Call the login method from the AuthService
    this.authService.login(email, pwd).subscribe({
      next: (data: any) => {
        console.log(data.token);

        // Store the token in both localStorage and sessionStorage
        localStorage.setItem('token', data.token);
        sessionStorage.setItem('token', data.token);
        // Retrieve the token from localStorage
        const token = localStorage.getItem('token');
        console.log("===================>" + token);
        // Decode the JWT token to get user information
        // @ts-ignore
        const loggedInUser = jwtDecode(token) as LoggedInUserInfo;
        console.log("role:==============>>>>>>", loggedInUser.role);
        // Redirect based on user role
        if (loggedInUser.role === "Admin") {
          this.route.navigateByUrl("/admin");
        } else {
          this.route.navigateByUrl("/dashboardBusiness");
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}
