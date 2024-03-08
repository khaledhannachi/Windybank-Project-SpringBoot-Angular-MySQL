import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BankService {
  private baseUrl = 'http://localhost:8080/api/v1';
  private personalUrl = 'http://localhost:8080/api/v1/personals/user/personal';
  constructor(private http: HttpClient) { }



  registerBusiness(business: any, userId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/businesses/newbusiness/${userId}`, business);
  }

  registerPersonal(personal: any, userId: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/personals/newpersonal/${userId}`, personal);
  }

  registerProfessional(professional: any, userId: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/professionals/newprofessional/${userId}`, professional);
  }

// ======================transfer & history===========================================
  saveTransfer(transfer: any): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/transfer`,
      transfer);
  }
  public getHistory(page : number=1, size:number=4,accountId:String):Observable<any[]>{
    return this.http.get <any[]>(`http://localhost:8080/api/v1/accounts/${accountId}/pageOperations?_page=${page}&_limit=${size}`);
  }
  // ======================transfer & history===========================================


  // ======================personal dashboard===========================================
  getPersonalByUserPersonalId(userId: number): Observable<any> {
    return this.http.get<any>(`${this.personalUrl}/${userId}`);
  }
  getPersonalBankAccount(accountId: String): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/accounts/personal/${accountId}`);
  }

  private accountDataSubjectPersonal = new BehaviorSubject<any>(null);
  setAccountDataPersonal(data: any): void {
    this.accountDataSubjectPersonal.next(data);
  }
  getAccountDataPersonal(): Observable<any> {
    return this.accountDataSubjectPersonal.asObservable();
  }
  // ======================personal dashboard===========================================



  // ======================get loggedInUser===========================================
  private logdinUserDataSubject = new BehaviorSubject<any>(null);
  setlogdinUserData(data: any): void {
    this.logdinUserDataSubject.next(data);
  }
  getlogdinUserData(): Observable<any> {
    return this.logdinUserDataSubject.asObservable();
  }
  // ======================loggedInUser===========================================


  // ======================Professional dashboard===========================================
  getProfessionalByUserProfessionalId(userId: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/professionals/user/professional/${userId}`);
  }
  getProfessionalBankAccount(accountId: String): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/accounts/professional/${accountId}`);
  }

  private accountDataSubjectProfessional = new BehaviorSubject<any>(null);
  setAccountDataProfessional(data: any): void {
    this.accountDataSubjectProfessional.next(data);
  }
  getAccountDataProfessional(): Observable<any> {
    return this.accountDataSubjectProfessional.asObservable();
  }
  // ======================Professional dashboard===========================================

  // ======================Business dashboard===========================================
  getBusinessByLegalResponsibleId(userId: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/businesses/user/business/${userId}`);
  }
  getBusinessBankAccount(accountId: String): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/accounts/business/${accountId}`);
  }

  private accountDataSubjectBusiness = new BehaviorSubject<any>(null);
  setAccountDataBusiness(data: any): void {
    this.accountDataSubjectBusiness.next(data);
  }
  getAccountDataBusiness(): Observable<any> {
    return this.accountDataSubjectBusiness.asObservable();
  }
  // ======================Business dashboard===========================================

  // ======================creations Accounts===========================================
  saveCheckingPersonalBankAccountDTO(personalID: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/checking/personal/${personalID}`,
      personalID);
  }
  saveSavingPersonalBankAccountDTO(personalID: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/saving/personal/${personalID}`,
      personalID);
  }

  saveCheckingProfessionalBankAccountDTO(professionalID: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/checking/professional/${professionalID}`,
      professionalID);
  }
  saveSavingProfessionalBankAccountDTO(professionalID: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/saving/professional/${professionalID}`,
      professionalID);
  }

  saveCheckingBusinessBankAccountDTO(businessID: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/checking/business/${businessID}`,
      businessID);
  }
  saveSavingBusinessBankAccountDTO(businessID: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/accounts/saving/business/${businessID}`,
      businessID);
  }
  // ======================creations Accounts===========================================



//   ==========================invoice========================================================

  public getInvoices():Observable<Array<any>>{
    return this.http.get<Array<any>>("http://localhost:8080/api/v1/invoices");
  }
  getInvoiceById(id: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/invoices/${id}`);
  }
  deleteInvoice(id: number): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/v1/invoices/${id}`);
  }
  createInvoice(invoice: any, businessId: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/invoices/newinvoice/${businessId}`, invoice);
  }
  public getProducts():Observable<Array<any>>{
    return this.http.get<Array<any>>("http://localhost:8080/api/v1/products");
  }
  getProductById(id: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/products/${id}`);
  }
  deleteProduct(id: number): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/v1/products/${id}`);
  }
  createProduct(product: any, businessId: number): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/v1/products/newproduct/${businessId}`, product);
  }

  addProductToInvoice(invoiceId: number, product: any): Observable<any> {

    return this.http.post(`http://localhost:8080/api/v1/invoices/${invoiceId}/addproducts`, product);
  }

//   =====================invoice================================================
}
