import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfessionalService {

  constructor(private http:HttpClient) { }

  public getProfessional():Observable<Array<any>>{
    return this.http.get<Array<any>>("http://localhost:8080/api/v1/professionals");
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
