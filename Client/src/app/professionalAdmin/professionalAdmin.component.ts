import { Component } from '@angular/core';
import {PersonelService} from "../services/personel.service";
import {ProfessionalService} from "../services/professional.service";

interface onInit {
}

@Component({
  selector: 'app-professionalAdmin',
  templateUrl: './professionalAdmin.component.html',
  styleUrls: ['./professionalAdmin.component.css']
})
export class ProfessionalAdminComponent implements onInit{

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
