import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { BankService } from "../services/bank.service";
import {LoggedInUserInfo} from "../services/logged-in-user-info";
import {Subscription} from "rxjs";
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
@Component({
  selector: 'app-AddToInvoiceAllproducts',
  templateUrl: './AddToInvoiceAllproducts.Component.html',
  styleUrls: ['./AddToInvoiceAllproducts.Component.css']
})
export class AddToInvoiceAllproductsComponent  implements OnInit {
  constructor(private bankService: BankService,private authService:AuthService,  private route: ActivatedRoute, private cdr: ChangeDetectorRef) {
  }

  public keyword: string = "";
  invoices: Array<any> = [];
  products: Array<any> = [];
  public business: any = null;
  public invoice: any = null;
  public loggedInUser: LoggedInUserInfo | null = null;
  private loggedInUserSubscription: Subscription | null = null;

  ngOnInit() {
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


    // Get the invoice ID from route parameters and call getInvoiceById
    this.route.params.subscribe(params => {
      const invoiceId = params['id'];
      if (invoiceId) {
        this.getInvoiceById(invoiceId);
      }
    });

    this.getInvoices();
    this.getProducts();
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

  onDeleteProduct(id: number): void {
    this.bankService.deleteProduct(id).subscribe(
      () => {
        console.log('Invoice deleted successfully.');
        this.getProducts(); // Refresh the data after deletion
      },
      error => {
        console.error('Error deleting product:', error);
        // Handle error scenarios if necessary
      }
    );
  }

  addProductsToInvoice(product: any) {
    if (!this.invoice) {
      console.error('No invoice selected.');
      return;
    }
    const invoiceId = this.invoice.id;
    this.bankService.addProductToInvoice(invoiceId, product).subscribe(
      (response: any) => {
        console.log('Products added to invoice successfully:', response);
        // Optionally, you can update UI or navigate to another page here
      },
      (error: any) => {
        console.error('Error adding products to invoice:', error);
        // Handle error appropriately, such as showing an error message
      }
    );
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

}




