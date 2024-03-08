import {Component, OnInit} from '@angular/core';

import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.css']
})
export class CreditCardComponent implements OnInit{
  // public business: any = null; // Initialize to null
  public personal: any = null; // Initialize to null

  public loggedInUser: LoggedInUserInfo | null = null; // Initialize to null

  private loggedInUserSubscription: Subscription | null = null;

  constructor(
    private route: ActivatedRoute,
    private bankService: BankService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
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
        // Handle error appropriately, e.g., display an error message
      }
    );
  }



  // getBusinessByLegalResponsibleId(userId:number): void {
  //   this.businessService.getBusinessByLegalResponsibleId(userId).subscribe({
  //     next: (data: any) => {
  //       this.business = data;
  //       console.log("******************************>>>>>>>>Business:", this.business.id);
  //     },
  //     error: (err: any) => {
  //       console.error('Error fetching business:', err);
  //     }
  //   });
  // }
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
}
