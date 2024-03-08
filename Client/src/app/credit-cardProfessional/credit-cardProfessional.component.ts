import {Component, OnInit} from '@angular/core';

import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-credit-cardProfessional',
  templateUrl: './credit-cardProfessional.component.html',
  styleUrls: ['./credit-cardProfessional.component.css']
})
export class CreditCardProfessionalComponent implements OnInit{

  public logdinUser!: any ;
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
          // this.getPersonalByUserPersonalId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);
        // Handle error appropriately, e.g., display an error message
      }
    );

    this.bankService.getlogdinUserData().subscribe(data1 => {
      this.logdinUser = data1; // Use the shared data in this component
    });
  }





}
