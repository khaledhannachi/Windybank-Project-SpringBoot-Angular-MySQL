import { Component } from '@angular/core';
import {PersonelService} from "../services/personel.service";

interface onInit {
}

@Component({
  selector: 'app-pending-acount-personel',
  templateUrl: './pending-acount-personel.component.html',
  styleUrls: ['./pending-acount-personel.component.css']
})
export class PendingAcountPersonelComponent implements onInit{


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


  ActiveAccount(bankAccountId:any){
    this.personelService.ActiveAccount(bankAccountId)
      .subscribe({
        next:data=>{

          this.ngOnInit();

        }
      })

  }


  DeletedAccount(bankAccountId:any){
    this.personelService.DeletedAccount(bankAccountId)
        .subscribe({
          next:data=>{

            this.ngOnInit();

          }
        })

  }



}
