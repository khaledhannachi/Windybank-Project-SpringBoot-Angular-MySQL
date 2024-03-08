import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { AuthService } from "../services/auth.service";
import { LoggedInUserInfo } from "../services/logged-in-user-info";
import { Subscription } from 'rxjs';
import {BankService} from "../services/bank.service";

@Component({
  selector: 'app-accountBusiness',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountBusinessComponent implements OnInit{

  public business: any = null;
  public account: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;

  private loggedInUserSubscription: Subscription | null = null;

  constructor(
    private route: ActivatedRoute,
    private bankService: BankService,
    private authService: AuthService,

  ) { }

  ngOnInit(): void {
    this.loggedInUserSubscription = this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
          this.bankService.setlogdinUserData(user); // Set the account data in the service
          this.getBusinessByLegalResponsibleId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);

      }
    );

  }


  getBusinessByLegalResponsibleId(userId:number): void {
    this.bankService.getBusinessByLegalResponsibleId(userId).subscribe({
      next: (data: any) => {
        this.business = data;
        console.log("=================>>>>>>>>business:", this.business.id);
      },
      error: (err: any) => {
        console.error('Error fetching business:', err);
      }
    });
  }
  getBusinessBankAccount(accountId:String): void {

    this.bankService.getBusinessBankAccount(accountId).subscribe({
      next: (data) => {
        this.account = data;
        console.log("=============>>>>>>>"+accountId);
        this.bankService.setAccountDataBusiness(data); // Set the account data in the service

      },
      error: (err) => {
        console.log(err);
        console.log("=============>>>>>>>"+accountId);
      }
    });

  }
}
