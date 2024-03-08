import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BusinessService {


  constructor(private http:HttpClient) { }

  public getBusiness():Observable<Array<any>>{
    return this.http.get<Array<any>>("http://localhost:8080/api/v1/businesses");
  }




  public ActiveAccount(bankAccountId:any):Observable<any>{
    return this.http.put<any>(`http://localhost:8080/api/v1/accounts/${bankAccountId}/activate`,bankAccountId);
  }



  public SuspendAccount(bankAccountId:any):Observable<any>{
    return this.http.put<any>(`http://localhost:8080/api/v1/accounts/${bankAccountId}/suspend`,bankAccountId);
  }


  public DeletedAccount(bankAccountId:any):Observable<any>{
    return this.http.put<any>(`http://localhost:8080/api/v1/accounts/${bankAccountId}/delete`,bankAccountId);
  }















}
