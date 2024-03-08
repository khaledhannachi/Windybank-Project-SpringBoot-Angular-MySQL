import { Component } from '@angular/core';
import {ProfessionalService} from "../services/professional.service";

interface onInit {
}

@Component({
  selector: 'app-suspentedaccount-professional',
  templateUrl: './suspentedaccount-professional.component.html',
  styleUrls: ['./suspentedaccount-professional.component.css']
})
export class SuspentedaccountProfessionalComponent implements onInit{

  constructor(private professionalService:ProfessionalService) {
  }


  professionals:Array<any>=[];


  ngOnInit() {
    this.professionalService.getProfessional().subscribe({
      next: data => {
        this.professionals = data;
      },
      error: error => {
        console.error('Error fetching data:', error);
      }
    });


  }


  ActiveAccount(bankAccountId:any){
    this.professionalService.ActiveAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }



  DeletedAccount(bankAccountId:any){
    this.professionalService.DeletedAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }













}
