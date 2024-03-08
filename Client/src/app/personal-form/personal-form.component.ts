import {Component, OnInit} from '@angular/core';
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {BankService} from "../services/bank.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-personal-form',
  templateUrl: './personal-form.component.html',
  styleUrls: ['./personal-form.component.css']
})
export class PersonalFormComponent implements OnInit {
  public business: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;
  formRegisterPersonal!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private bankService: BankService,
    private router: Router
  ) {}

  ngOnInit() {
    this.formRegisterPersonal = this.fb.group({

      idType: this.fb.control(''),
      idNumber: this.fb.control(''),
      idDeliveryDate: this.fb.control(''),
      idDeliveryCity: this.fb.control(''),
      profession: this.fb.control(''),
      employerName: this.fb.control(''),
      employerAdress: this.fb.control(''),
      netPay: this.fb.control(''),
    });

    this.authService.getLoggedInUser().subscribe(
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

  handleRegisterPersonal(userId: number) {
    const personalObj = this.formRegisterPersonal.value;
    this.bankService.registerPersonal(personalObj, userId).subscribe({
      next: (data: any) => {
        this.router.navigateByUrl(`/dashboardPersonal`);
        console.log("55555555555555555555=======>",userId);
      },
      error: (err) => {
        console.error('Error registering business:', err);
      }
    });
  }
}
