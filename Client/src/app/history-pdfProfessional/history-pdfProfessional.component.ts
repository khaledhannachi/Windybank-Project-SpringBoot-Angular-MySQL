import {Component, OnInit} from '@angular/core';
import {BankService} from "../services/bank.service";

@Component({
  selector: 'app-history-pdfProfessional',
  templateUrl: './history-pdfProfessional.component.html',
  styleUrls: ['./history-pdfProfessional.component.css']
})
export class HistoryPdfProfessionalComponent implements OnInit{
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
