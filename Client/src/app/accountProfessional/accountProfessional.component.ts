import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { AuthService } from "../services/auth.service";
import { LoggedInUserInfo } from "../services/logged-in-user-info";
import { Subscription } from 'rxjs';
import {BankService} from "../services/bank.service";

@Component({
  selector: 'app-accountProfessional',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountProfessionalComponent implements OnInit{

  public professional: any = null;
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
          this.getProfessionalByUserProfessionalId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);

      }
    );

  }


  getProfessionalByUserProfessionalId(userId:number): void {
    this.bankService.getProfessionalByUserProfessionalId(userId).subscribe({
      next: (data: any) => {
        this.professional = data;
        console.log("=================>>>>>>>>professional:", this.professional.id);
      },
      error: (err: any) => {
        console.error('Error fetching professional:', err);
      }
    });
  }
  getProfessionalBankAccount(accountId:String): void {

    this.bankService.getProfessionalBankAccount(accountId).subscribe({
      next: (data) => {
        this.account = data;
        console.log("=============>>>>>>>"+accountId);
        this.bankService.setAccountDataProfessional(data); // Set the account data in the service

      },
      error: (err) => {
        console.log(err);
        console.log("=============>>>>>>>"+accountId);
      }
    });

  }
}
