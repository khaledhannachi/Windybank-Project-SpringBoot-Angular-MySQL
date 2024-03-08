import {Component, OnInit} from '@angular/core';
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-dashoard-Professional',
  templateUrl: './dashoard-Professional.component.html',
  styleUrls: ['./dashoard-Professional.component.css']
})
export class DashoardProfessionalComponent implements OnInit{
  public professional: any = null;

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

      },
      error: (err: any) => {
        console.error('Error fetching professional:', err);
      }
    });
  }

  saveCheckingProfessionalBankAccountDTO(professionalID:number): void {
    this.bankService.saveCheckingProfessionalBankAccountDTO(professionalID).subscribe({
      next: (data: any) => {
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
      },
      error: (err: any) => {
        console.error('Error fetching account:', err);
      }
    });
  }

  saveSavingProfessionalBankAccountDTO(professionalID:number): void {
    this.bankService.saveSavingProfessionalBankAccountDTO(professionalID).subscribe({
      next: (data: any) => {
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
      },
      error: (err: any) => {
        console.error('Error fetching account:', err);
      }
    });
  }

}
