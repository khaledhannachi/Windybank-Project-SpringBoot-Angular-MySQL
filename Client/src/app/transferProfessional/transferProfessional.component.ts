import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-transferProfessional',
  templateUrl: './transferProfessional.component.html',
  styleUrls: ['./transferProfessional.component.css']
})
export class TransferProfessionalComponent implements OnInit{
  public transferForm!:FormGroup;

  public professional: any = null; // Initialize to null

  public loggedInUser: LoggedInUserInfo | null = null; // Initialize to null

  private loggedInUserSubscription: Subscription | null = null;
  constructor(private fb: FormBuilder, private route: ActivatedRoute,
              private bankService: BankService,
              private authService: AuthService) {
  }
  ngOnInit() {
    this.transferForm=this.fb.group({
      accountSource: this.fb.control(this.professional?.professionalBankAccountsDTO[0].id, ),
      accountDestination : this.fb.control('', ),
      amount : this.fb.control('',),
      description : this.fb.control('',),
    });

    this.loggedInUserSubscription = this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
          // this.getBusinessByLegalResponsibleId(user.userId);
          this.getProfessionalByUserProfessionalId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);
        // Handle error appropriately, e.g., display an error message
      }
    );
  }

  saveTransfer() {
    let transfer:any=this.transferForm.value;
    this.bankService.saveTransfer(transfer).subscribe({
      next : data => {
        alert(JSON.stringify(data));
        console.log(this.transferForm.value);
      }, error :err => {
        console.log(err);
        console.log(this.transferForm.value);
      }
    });
  }

  getProfessionalByUserProfessionalId(userId:number): void {
    this.bankService.getProfessionalByUserProfessionalId(userId).subscribe({
      next: (data: any) => {
        this.professional = data;
        console.log("==========>>>>>>>>professional:", this.professional.id);
      },
      error: (err: any) => {
        console.error('Error fetching professional:', err);
      }
    });
  }

}
