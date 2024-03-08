import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  public getUsers():Observable<Array<any>>{
    return this.http.get<Array<any>>("http://localhost:8080/api/v1/users");
  }



  public searchUser(keyword:string):Observable<Array<any>>{
    return this.http.get<Array<any>>(`http://localhost:8080/api/v1/search?keyword=${keyword}`);
  }





}
