import {Component} from '@angular/core';
// @ts-ignore
import html2pdf from 'html2pdf.js';
import {RibPageComponent} from "../rib-pagePersonal/rib-page.component";

import {BankService} from "../services/bank.service";
import {RibPageProfessionalComponent} from "../rib-pageProfessional/rib-pageProfessional.component";

@Component({
  selector: 'app-accounts-pageProfessional',
  templateUrl: './accounts-pageProfessional.component.html',
  styleUrls: ['./accounts-pageProfessional.component.css'],


})
export class AccountsPageProfessionalComponent {
  public account: any = null;
  constructor( private rib:RibPageProfessionalComponent ,private bankService: BankService) {}

  //   pdf====================
  callSharedFunction(): void {
    this.rib.downloadPdf();
  }


  downloadHistoryPdf(): void {
    const element = document.getElementById('historyPDF'); // Add an ID to the parent element

    if (element) {
      const pdfOptions = {
        margin: 10,
        filename: 'account_history.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
      };

      html2pdf(element, pdfOptions);
    }
  }
}
