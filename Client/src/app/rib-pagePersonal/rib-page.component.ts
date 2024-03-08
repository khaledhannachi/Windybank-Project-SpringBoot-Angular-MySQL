import {Component, Injectable, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";

import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";
// @ts-ignore
import html2pdf from 'html2pdf.js';
@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-rib-page',
  templateUrl: './rib-page.component.html',
  styleUrls: ['./rib-page.component.css']
})
export class RibPageComponent implements  OnInit{

  public personal: any = null;
  public account: any = null;
  public loggedInUser: LoggedInUserInfo | null = null; // Initialize to null

  private loggedInUserSubscription: Subscription | null = null;
  constructor(private fb: FormBuilder,private route: ActivatedRoute,
              private bankService: BankService,
              private authService: AuthService) {
  }
  ngOnInit() {
// this.getPersonalBankAccount();

    this.loggedInUserSubscription = this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
          // this.getBusinessByLegalResponsibleId(user.userId);
          this.getPersonalByUserPersonalId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);

      }
    );
    this.bankService.getAccountDataPersonal().subscribe(data => {
      this.account = data; // Use the shared data in this component
    });
  }

  getPersonalByUserPersonalId(userId:number): void {
    this.bankService.getPersonalByUserPersonalId(userId).subscribe({
      next: (data: any) => {
        this.personal = data;
        console.log("******************************>>>>>>>>personal:", this.personal.id);
        // this.getPersonalBankAccount(this.personal.personalBankAccountsDTO[0].id);
      },
      error: (err: any) => {
        console.error('Error fetching personal:', err);
      }
    });
  }



//   pdf====================
  downloadPdf(): void {
    const element = document.getElementById('pdf-content'); // Add an ID to the parent element

    if (element) {
      const pdfOptions = {
        margin: 10,
        filename: 'account_details.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
      };

      html2pdf(element, pdfOptions);
    }
  }
}
