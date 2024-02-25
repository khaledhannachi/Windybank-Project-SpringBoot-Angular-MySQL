import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  formData: any = {};
  firstName: string = "";
  lastName: string = "";
  birthDay: Date = new Date();
  countryOfBirth: string = "";
  street: string = "";
  city: string = "";
  zipCode: number = 0;
  countryOfResidence: string = "";
  telephone: string = "";
  nationality: string = "";
  email: string = "";
  password: string = "";
  confirm: string = "";

  constructor(private http: HttpClient, private router: Router) {}

  save() {
    let bodyData = {
      "firstName": this.firstName,
      "lastName": this.lastName,
      "birthDay": this.birthDay,
      "countryOfBirth": this.countryOfBirth,
      "street": this.street,
      "city": this.city,
      "zipCode": this.zipCode,
      "countryOfResidence": this.countryOfResidence,
      "telephone": this.telephone,
      "nationality": this.nationality,
      "email": this.email,
      "password": this.password,
      "confirm": this.confirm
    };

    this.http.post("http://localhost:8080/api/v1/users/register", this.formData)
      .subscribe(
        (resultData: any) => {
          console.log(resultData);
          this.router.navigate(['/home']); 
        },
        (error) => {
          console.error("Error:", error);
        }
      );
  }
}
