import { Component } from '@angular/core';
import {PersonelService} from "../services/personel.service";

interface onInit {
}

@Component({
  selector: 'app-personelAdmin',
  templateUrl: './personelAdmin.component.html',
  styleUrls: ['./personelAdmin.component.css']
})
export class PersonelAdminComponent implements onInit{

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
