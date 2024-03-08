import {Component, OnInit} from '@angular/core';
import {BankService} from "../services/bank.service";

@Component({
  selector: 'app-history-pdfBusiness',
  templateUrl: './history-pdfBusiness.component.html',
  styleUrls: ['./history-pdfBusiness.component.css']
})
export class HistoryPdfBusinessComponent implements OnInit{
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
