import {Component, OnInit} from '@angular/core';
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-dashoard-Business',
  templateUrl: './dashoard-Business.component.html',
  styleUrls: ['./dashoard-Business.component.css']
})
export class DashoardBusinessComponent implements OnInit{
  public business: any = null;

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
        console.log("******************************>>>>>>>>Business:", this.business.id);
      },
      error: (err: any) => {
        console.error('Error fetching business:', err);
      }
    });
  }

  saveCheckingBusinessBankAccountDTO(BusinessID:number): void {
    this.bankService.saveCheckingBusinessBankAccountDTO(BusinessID).subscribe({
      next: (data: any) => {
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
      },
      error: (err: any) => {
        console.error('Error fetching account:', err);
      }
    });
  }

  saveSavingBusinessBankAccountDTO(BusinessID:number): void {
    this.bankService.saveSavingBusinessBankAccountDTO(BusinessID).subscribe({
      next: (data: any) => {
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
      },
      error: (err: any) => {
        console.error('Error fetching account:', err);
      }
    });
  }

}
