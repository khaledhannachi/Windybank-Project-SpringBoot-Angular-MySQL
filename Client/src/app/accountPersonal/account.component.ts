import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { AuthService } from "../services/auth.service";
import { LoggedInUserInfo } from "../services/logged-in-user-info";
import { Subscription } from 'rxjs';
import {BankService} from "../services/bank.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit{

  public personal: any = null;
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
          this.getPersonalByUserPersonalId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);

      }
    );

  }


  getPersonalByUserPersonalId(userId:number): void {
    this.bankService.getPersonalByUserPersonalId(userId).subscribe({
      next: (data: any) => {
        this.personal = data;
        console.log("******************************>>>>>>>>Business:", this.personal.id);
      },
      error: (err: any) => {
        console.error('Error fetching business:', err);
      }
    });
  }
  getPersonalBankAccount(accountId:String): void {

    this.bankService.getPersonalBankAccount(accountId).subscribe({
      next: (data) => {
        this.account = data;
        console.log("=============>>>>>>>"+accountId);
        this.bankService.setAccountDataPersonal(data); // Set the account data in the service

      },
      error: (err) => {
        console.log(err);
        console.log("=============>>>>>>>"+accountId);
      }
    });

  }
}
