import {Component, OnInit} from '@angular/core';

import {BankService} from "../services/bank.service";


@Component({
  selector: 'app-show-one-accountProfessional',
  templateUrl: './show-one-accountProfessional.component.html',
  styleUrls: ['./show-one-accountProfessional.component.css']
})

export class ShowOneAccountProfessionalComponent implements OnInit{
  public account!: any ;
  public logdinUser!: any ;

  constructor(
              private bankService: BankService,
              ) {
  }
  ngOnInit(): void {
    this.bankService.getAccountDataProfessional().subscribe(data => {
      this.account = data; // Use the shared data in this component
    });
    this.bankService.getlogdinUserData().subscribe(data1 => {
      this.logdinUser = data1; // Use the shared data in this component
    });
  }

}
