import {Component, OnInit} from '@angular/core';
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {BankService} from "../services/bank.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-professional-form',
  templateUrl: './professional-form.component.html',
  styleUrls: ['./professional-form.component.css']
})
export class ProfessionalFormComponent implements OnInit {
  public business: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;
  formRegisterProfessional!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private bankService: BankService,
    private router: Router
  ) {}

  ngOnInit() {
    this.formRegisterProfessional = this.fb.group({
      taxIdentificationNumber: this.fb.control(''),
      businessRegistration: this.fb.control(''),
      profession: this.fb.control(''),
      companyName: this.fb.control(''),
      companyAdress: this.fb.control(''),
      cnssNumber: this.fb.control(''),
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

  handleRegisterProfessional(userId: number) {
    const proObj = this.formRegisterProfessional.value;
    this.bankService.registerProfessional(proObj, userId).subscribe({
      next: (data: any) => {
        this.router.navigateByUrl(`/dashboardProfessional`);
      },
      error: (err) => {
        console.error('Error registering professional:', err);
      }
    });
  }
}
