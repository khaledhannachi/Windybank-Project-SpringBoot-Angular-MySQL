import { Component } from '@angular/core';
import {BusinessService} from "../services/business.service";

interface onInit {
}

@Component({
  selector: 'app-activedaccountbusiness',
  templateUrl: './activedaccountbusiness.component.html',
  styleUrls: ['./activedaccountbusiness.component.css']
})
export class ActivedaccountbusinessComponent implements onInit{


  constructor(private businessService:BusinessService) {
  }

  allbusiness:Array<any>=[];


  ngOnInit() {
    this.businessService.getBusiness().subscribe({
      next: data => {
        this.allbusiness = data;
      },
      error: error => {
        console.error('Error fetching data:', error);
      }
    });


  }



  SuspendAccount(bankAccountId:any){
    this.businessService.SuspendAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }






}
