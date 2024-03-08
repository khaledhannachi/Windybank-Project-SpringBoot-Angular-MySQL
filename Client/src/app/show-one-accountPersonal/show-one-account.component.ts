import {Component, OnInit} from '@angular/core';

import {BankService} from "../services/bank.service";


@Component({
  selector: 'app-show-one-account',
  templateUrl: './show-one-account.component.html',
  styleUrls: ['./show-one-account.component.css']
})

export class ShowOneAccountComponent implements OnInit{
  public account!: any ;
  public logdinUser!: any ;

  constructor(
              private bankService: BankService,
              ) {
  }
  ngOnInit(): void {
    this.bankService.getAccountDataPersonal().subscribe(data => {
      this.account = data; // Use the shared data in this component
    });
    this.bankService.getlogdinUserData().subscribe(data1 => {
      this.logdinUser = data1; // Use the shared data in this component
    });
  }

}
