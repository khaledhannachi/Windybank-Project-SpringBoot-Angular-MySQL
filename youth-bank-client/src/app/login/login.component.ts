import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = "";
  password: string = "";

  constructor(private router: Router, private http: HttpClient) {}

  login() {
    const bodyData = {
      email: this.email,
      password: this.password
    };

    this.http.post<any>("http://localhost:8080/api/v1/users/login", bodyData).subscribe(
      resultData => {
        if (resultData.message === "Login Success") {
          this.router.navigateByUrl('/home');
        } else {
          alert("Incorrect Email and Password combination.");
        }
      },
      error => {
        console.error('Error:', error);
        alert("An error occurred during login.");
      }
    );
  }
}
