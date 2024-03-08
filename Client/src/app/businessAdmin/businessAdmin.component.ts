import { Component } from '@angular/core';
import {ProfessionalService} from "../services/professional.service";
import {BusinessService} from "../services/business.service";

interface onInit {
}

@Component({
  selector: 'app-businessAdmin',
  templateUrl: './businessAdmin.component.html',
  styleUrls: ['./businessAdmin.component.css']
})
export class BusinessAdminComponent implements onInit{

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




  ActiveAccount(bankAccountId:any){
    this.businessService.ActiveAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }



  DeletedAccount(bankAccountId:any){
    this.businessService.DeletedAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }









}
