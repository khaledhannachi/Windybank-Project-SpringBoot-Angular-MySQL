import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  formRegister!:FormGroup;
  constructor(private fb: FormBuilder, private authService: AuthService, private route: Router) {
  }
  ngOnInit() {
    this.formRegister=this.fb.group({
      firstName : this.fb.control('',),
      lastName : this.fb.control('',),
      email: this.fb.control('',),
      telephone : this.fb.control('',),
      password : this.fb.control('',),
      confirm : this.fb.control('',),
      birthDay : this.fb.control('',),
      countryOfBirth : this.fb.control('',),
      street : this.fb.control('',),
      city : this.fb.control('',),
      zipCode : this.fb.control('',),
      countryOfResidence : this.fb.control('',),
      nationality : this.fb.control('',),
    });
  }
  handleRegister() {
    let user:any=this.formRegister.value;
    this.authService.register(user).subscribe({
      next: (data: any) => {
        console.log(data.token);
        localStorage.setItem('token', data.token);
        sessionStorage.setItem('token', data.token);
        this.route.navigateByUrl("/personaltab")
        console.log("===================>"+data.token.userId);
        console.log("===================>"+data.token.firstName);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}


