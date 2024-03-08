import { Component, OnInit, OnDestroy } from '@angular/core';

import { LoggedInUserInfo } from '../services/logged-in-user-info';
import { AuthService } from '../services/auth.service';
import { Subscription } from 'rxjs';
import { BankService } from '../services/bank.service';

const DEFAULT_PAGE_INDEX = 2;
const DEFAULT_PAGE_SIZE = 2;

@Component({
  selector: 'app-historyBusiness',
  templateUrl: './historyBusinesscomponent.html',
  styleUrls: ['./historyBusinesscomponent.css']
})
export class HistoryBusinesscomponent implements OnInit, OnDestroy {

  public history: any = null;
  public business: any = null;
  public account: any = null;

  public loggedInUser: LoggedInUserInfo | null = null;

  private loggedInUserSubscription: Subscription | null = null;

  constructor(

    private authService: AuthService,
    private bankService: BankService,
  ) {}

  ngOnInit(): void {
    this.loggedInUserSubscription = this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
          this.getBusinessByLegalResponsibleId(user.userId);
          this.getAccountDataBusiness();
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);
        // Handle error appropriately, e.g., display an error message
      }
    );


  }

  ngOnDestroy(): void {
    if (this.loggedInUserSubscription) {
      this.loggedInUserSubscription.unsubscribe();
    }
  }
  // Fetch account data
  getAccountDataBusiness(): void {
    this.bankService.getAccountDataBusiness().subscribe(data => {
      this.account = data;
      console.log('Account data:', this.account);
      // Optionally, you can trigger fetching history here if needed
      this.getHistory();
    });
  }
  getHistory(): void {
    // Ensure that the account object is available before fetching history
    if (this.account && this.account.id) {
      const accountId = this.account.id;
      this.bankService.getHistory(0, 5, accountId).subscribe({
        next: (data) => {
          this.history = data;
        },
        error: (err) => {
          console.log(err);
        }
      });
    } else {
      console.warn('Account data is not available. Unable to fetch history.');
    }
  }

  getBusinessByLegalResponsibleId(userId: number): void {
    this.bankService.getBusinessByLegalResponsibleId(userId).subscribe({
      next: (data: any) => {
        this.business = data;
        console.log('business:', this.business.id);

      },
      error: (err: any) => {
        console.error('Error fetching business:', err);
      }
    });
  }

}
