import { Component } from '@angular/core';
import {PersonelService} from "../services/personel.service";

interface onInit {
}

@Component({
  selector: 'app-activedaccount-personel',
  templateUrl: './activedaccount-personel.component.html',
  styleUrls: ['./activedaccount-personel.component.css']
})
export class ActivedaccountPersonelComponent  implements onInit{

  constructor(private personelService:PersonelService) {
  }


  personels:Array<any>=[];

  ngOnInit() {
    this.personelService.getPersonnel().subscribe({
      next: data => {
        this.personels = data;
      },
      error: error => {
        console.error('Error fetching data:', error);
      }
    });


  }





  SuspendAccount(bankAccountId:any){
    this.personelService.SuspendAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }





}
