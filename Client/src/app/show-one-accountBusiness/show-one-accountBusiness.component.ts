import {Component, OnInit} from '@angular/core';

import {BankService} from "../services/bank.service";


@Component({
  selector: 'app-show-one-accountBusiness',
  templateUrl: './show-one-accountBusiness.component.html',
  styleUrls: ['./show-one-accountBusiness.component.css']
})

export class ShowOneAccountBusinessComponent implements OnInit{
  public account!: any ;
  public logdinUser!: any ;

  constructor(
              private bankService: BankService,
              ) {
  }
  ngOnInit(): void {
    this.bankService.getAccountDataBusiness().subscribe(data => {
      this.account = data; // Use the shared data in this component
    });
    this.bankService.getlogdinUserData().subscribe(data1 => {
      this.logdinUser = data1; // Use the shared data in this component
    });
  }

}
