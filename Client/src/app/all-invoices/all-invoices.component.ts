import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { BankService } from "../services/bank.service";
@Component({
  selector: 'app-all-invoices',
  templateUrl: './all-invoices.component.html',
  styleUrls: ['./all-invoices.component.css']
})
export class AllInvoicesComponent implements OnInit {
  constructor(private bankService: BankService, private cdr: ChangeDetectorRef) {}

  public keyword: string = "";
  invoices: Array<any> = [];
  products: Array<any> = [];
  ngOnInit() {
    this.getInvoices();
  }
  getInvoices() {
    this.bankService.getInvoices().subscribe({
      next: data => {
        this.invoices = data;
        this.cdr.detectChanges(); // Trigger change detection
      },
      error: error => {
        console.error('Error fetching data:', error);
      }
    });
  }
  onDeleteClick(id: number): void {
    this.bankService.deleteInvoice(id).subscribe(
      () => {
        console.log('Invoice deleted successfully.');
        this.getInvoices(); // Refresh the data after deletion
      },
      error => {
        console.error('Error deleting invoice:', error);
        // Handle error scenarios if necessary
      }
    );
  }
}
