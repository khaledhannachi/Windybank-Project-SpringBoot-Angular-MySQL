import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";
import { BankService } from "../services/bank.service";
import { LoggedInUserInfo } from "../services/logged-in-user-info";
import { ActivatedRoute } from "@angular/router";
@Component({
  selector: 'app-invoice-form',
  templateUrl: './invoice-form.component.html', // Ensure this path is correct
  styleUrls: ['./invoice-form.component.css']
})
export class InvoiceFormComponent implements OnInit {
  public business: any = null;
  public invoice: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;
  formCreateInvoice!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private bankService: BankService,
    private route: Router,
    private activeRoute:ActivatedRoute,
  ) {
  }
  ngOnInit() {
    // Initialize the form group first
    this.initializeForm();

    // Fetch logged-in user information
    this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
          this.getBusinessByLegalResponsibleId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);
      }
    );
  }

  initializeForm() {
    this.formCreateInvoice = this.fb.group({
      beneficiary: [''],
      phoneNumber: [''],
      taxIdentificationNumber: [''],
      email: [''],
      sectorOfActivity: [''],
      activityStartDate: [''],
      activity: [''],
      discount: [''],
      tva: [''],
      timbre: [''],
    });
  }
  getBusinessByLegalResponsibleId(userId: number): void {
    this.bankService.getBusinessByLegalResponsibleId(userId).subscribe({
      next: (data: any) => {
        this.business = data;
        console.log("Business:", this.business.id);
        // this. handleCreateInvoice(this.business.id);
      },
      error: (err: any) => {
        console.error('Error fetching business:', err);
      }
    });
  }
  handleCreateInvoice(businessid:number) {
      let invoice:any=this.formCreateInvoice .value;
      this.bankService.createInvoice(invoice, businessid).subscribe({
        next: (data: any) => {
          console.log("=====>>>>>"+data.id);
          this.route.navigateByUrl(`/inventory/${data.id}`)
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
}
