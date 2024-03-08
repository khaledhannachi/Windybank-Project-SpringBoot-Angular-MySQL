import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";
import { BankService } from "../services/bank.service";
import { LoggedInUserInfo } from "../services/logged-in-user-info";

@Component({
  selector: 'app-business-form',
  templateUrl: './business-form.component.html',
  styleUrls: ['./business-form.component.css']
})
export class BusinessFormComponent implements OnInit {
  public business: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;
  formRegisterBusiness!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private bankService: BankService,
    private router: Router
  ) {}

  ngOnInit() {
    this.formRegisterBusiness = this.fb.group({
      commercialName: [''],
      companylegalName:[''],
      taxIdentificationNumber: [''],
      businessRegistration: [''],
      cnssNumber: [''],
      activityStartDate:[''],
      activity: [''],
      turnoverLatestFinancialYear:[''],
      resultOfTheLastFinancialYear:[''],
      companyStreet:[''],
      companyCity:[''],
      companyPostalCode:[''],
      companyPhone: [''],
      companyEmail: [''],

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

  handleRegisterBusiness(userId: number) {
    const businessObj = this.formRegisterBusiness.value;
    this.bankService.registerBusiness(businessObj, userId).subscribe({
      next: (data: any) => {
        this.router.navigateByUrl(`/dashboardBusiness`);
      },
      error: (err) => {
        console.error('Error registering business:', err);
      }
    });
  }
}
