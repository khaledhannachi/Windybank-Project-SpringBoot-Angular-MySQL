import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {jwtDecode} from "jwt-decode";
import {LoggedInUserInfo} from "../services/logged-in-user-info";

@Component({
  selector: 'app-login-professionals',
  templateUrl: './login-professionals.component.html',
  styleUrls: ['./login-professionals.component.css']
})
export class LoginProfessionalsComponent implements OnInit {
  formLogin!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService,private route: Router) {}

  ngOnInit() {
    this.formLogin = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  handleLogin() {
    let email = this.formLogin.value.email;
    let pwd = this.formLogin.value.password;
    this.authService.login(email, pwd).subscribe({
      next: (data: any) => {
        console.log(data.token);
        localStorage.setItem('token', data.token);
        sessionStorage.setItem('token', data.token);
        const token = localStorage.getItem('token');
        // Decode the JWT token to get user information
        // @ts-ignore
        const loggedInUser = jwtDecode(token) as LoggedInUserInfo;
        console.log("role:==============>>>>>>", loggedInUser.role);
        // Redirect based on user role
        if (loggedInUser.role === "Admin") {
          this.route.navigateByUrl("/admin");
        } else {
          this.route.navigateByUrl("/dashboardProfessional");
        }

      },
      error: (err) => {
        console.log(err);
      }
    });
  }

}
