import { Component, OnDestroy, OnInit, ChangeDetectorRef } from '@angular/core';
import { LoggedInUserInfo } from "../services/logged-in-user-info";
import { Subscription } from "rxjs";
import { ActivatedRoute } from "@angular/router";
import { BankService } from "../services/bank.service";
import { AuthService } from "../services/auth.service";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit, OnDestroy {
  public business: any = null;
  public invoice: any = null;
  public product: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;
  private loggedInUserSubscription: Subscription | null = null;
  invoices: Array<any> = [];
  products: Array<any> = [];
  public keyword: string = "";
  totalAmountForAllInvoices: number = 0; // Variable to hold the sum of total amounts for all invoices

  constructor(
    private route: ActivatedRoute,
    private bankService: BankService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef // Import ChangeDetectorRef for triggering change detection
  ) {}

  ngOnInit(): void {
    this.loggedInUserSubscription = this.authService.getLoggedInUser().subscribe(
      (user: LoggedInUserInfo | null) => {
        if (user) {
          this.loggedInUser = user;
          this.getBusinessByLegalResponsibleId(user.userId);
        }
      },
      (error) => {
        console.error('Error fetching logged-in user:', error);
      }
    );

    // Get the invoice ID from route parameters and call getInvoiceById
    this.route.params.subscribe(params => {
      const invoiceId = params['id'];
      if (invoiceId) {
        this.getInvoiceById(invoiceId);
      }
    });

    // Fetch all invoices
    this.getInvoices();
  }

  ngOnDestroy(): void {
    // Unsubscribe from the loggedInUserSubscription to avoid memory leaks
    if (this.loggedInUserSubscription) {
      this.loggedInUserSubscription.unsubscribe();
    }
    this.getInvoiceById(this.invoice.id);
  }

  getBusinessByLegalResponsibleId(userId: number): void {
    this.bankService.getBusinessByLegalResponsibleId(userId).subscribe({
      next: (data: any) => {
        this.business = data;
        console.log("******************************>>>>>>>>Business:", this.business ? this.business.id : null);
      },
      error: (err: any) => {
        console.error('Error fetching business:', err);
      }
    });
  }
  getInvoices() {
    this.bankService.getInvoices().subscribe({
      next: data => {
        this.invoices = data;
        this.calculateTotalAmountForAllInvoices(); // Calculate the sum of total amounts for all invoices
        this.cdr.detectChanges(); // Trigger change detection
      },
      error: error => {
        console.error('Error fetching data:', error);
      }
    });
  }

  getInvoiceById(invoiceId: number): void {
    this.bankService.getInvoiceById(invoiceId).subscribe({
      next: (data) => {
        this.invoice = data;
        console.log("this an array =====>", this.invoice ? this.invoice.choosedProductsDTO : null);
      },
      error: (err) => {
        console.error('Error fetching invoice:', err);
        console.log("=============>>>>>>>", invoiceId);
      }
    });
  }

  onPrint(): void {
    window.print();
  }

  calculateTotal(): number {
    if (!this.invoice || !this.invoice.choosedProductsDTO) {
      return 0;
    }
    return this.invoice.choosedProductsDTO.reduce((total: any, product: { price: any; }) => total + product.price, 0);
  }

  calculateTotalTax(): number {
    if (!this.invoice) {
      return 0;
    }
    const total = this.calculateTotal();
    const tva = this.invoice.tva || 0;
    const timbre = this.invoice.timbre || 0;
    return (total * tva / 100) + timbre;
  }

  calculateDiscountedTotal(): number {
    if (!this.invoice) {
      return 0;
    }
    const total = this.calculateTotal();
    const discount = this.invoice.discount || 0;
    return total - (total * discount / 100);
  }

  calculateTotalAmount(): number {
    const discountedTotal = this.calculateDiscountedTotal();
    const totalTax = this.calculateTotalTax();
    return discountedTotal + totalTax;
  }

  // Method to calculate the sum of total amounts for all invoices
  calculateTotalAmountForAllInvoices(): void {
    this.totalAmountForAllInvoices = this.invoices.reduce((total: number, invoice: any) => {
      const discountedTotal = invoice.choosedProductsDTO.reduce((acc: number, product: any) => {
        return acc + (product.price - (product.price * invoice.discount / 100));
      }, 0);
      const totalTax = ((discountedTotal * invoice.tva / 100) + invoice.timbre);
      return total + (discountedTotal + totalTax);
    }, 0);
  }
}
