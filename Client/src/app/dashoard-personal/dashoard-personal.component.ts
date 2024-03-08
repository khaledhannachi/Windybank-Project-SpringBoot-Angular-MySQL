import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";

@Component({
  selector: 'app-dashoard-personal',
  templateUrl: './dashoard-personal.component.html',
  styleUrls: ['./dashoard-personal.component.css']
})
export class DashoardPersonalComponent implements OnInit{
  public personal: any = null;

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

  saveCheckingPersonalBankAccountDTO(personalID:number): void {
    this.bankService.saveCheckingPersonalBankAccountDTO(personalID).subscribe({
      next: (data: any) => {
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

      },
      error: (err: any) => {
        console.error('Error fetching account:', err);
      }
    });
  }

  saveSavingPersonalBankAccountDTO(personalID:number): void {
    this.bankService.saveSavingPersonalBankAccountDTO(personalID).subscribe({
      next: (data: any) => {
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
      },
      error: (err: any) => {
        console.error('Error fetching account:', err);
      }
    });
  }

}
