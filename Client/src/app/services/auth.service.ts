import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {jwtDecode} from "jwt-decode";
import {LoggedInUserInfo} from "./logged-in-user-info";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/api/v1/login';
  private registerUrl = 'http://localhost:8080/api/v1/register';

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl, {email, password});
  }

  register(user: any): Observable<any> {
    return this.http.post<any>(this.registerUrl, user);
  }
  logout(): Observable<any> {
    localStorage.removeItem('token');
    return this.http.post('http://localhost:8080/api/v1/logout', {}).pipe();
  }
  getLoggedInUser(): Observable<LoggedInUserInfo | null> {
    const token = localStorage.getItem('token');
    if (token) {
      const loggedInUser = jwtDecode(token) as LoggedInUserInfo;
      console.log("User ID:==============>>>>>>", loggedInUser.userId);
      console.log("User ID:==============>>>>>>", loggedInUser.role);
      return of(loggedInUser); // Import 'of' from 'rxjs' if necessary

    } else {
      return of(null); // Return an observable with null value if token is not found
    }
  }

  }
