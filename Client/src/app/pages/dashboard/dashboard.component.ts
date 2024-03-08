import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import{BankService} from "../../services/bank.service";
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  date: number;
  keyword: string = "";
  invoices: any[] = [];
  products: any[] = [];
  constructor(private bankService: BankService, private cdr: ChangeDetectorRef) {
    this.date = new Date().getFullYear();
  }

  ngOnInit() {
    this.getInvoices();
  }

  getInvoices() {
    this.bankService.getInvoices().subscribe({
      next: (data: any) => {
        this.invoices = data;
        this.cdr.detectChanges(); // Trigger change detection
      },
      error: (error: any) => {
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
      (error: any) => {
        console.error('Error deleting invoice:', error);
        // Handle error scenarios if necessary
      }
    );
  }

  getProducts() {
    this.bankService.getProducts().subscribe({
      next: data => {
        this.products = data;
        this.cdr.detectChanges(); // Trigger change detection
        console.log("======all products===============", this.products);
      },
      error: error => {
        console.error('Error fetching data:', error);
      }
    });
  }
}
