import { Component } from '@angular/core';
import {PersonelService} from "../services/personel.service";
import {ProfessionalService} from "../services/professional.service";

interface onInit {
}

@Component({
  selector: 'app-activedaccount-professional',
  templateUrl: './activedaccount-professional.component.html',
  styleUrls: ['./activedaccount-professional.component.css']
})
export class ActivedaccountProfessionalComponent implements onInit{


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



  SuspendAccount(bankAccountId:any){
    this.professionalService.SuspendAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }









}
