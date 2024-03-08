import { Component } from '@angular/core';
import {PersonelService} from "../services/personel.service";

interface onInit {
}

@Component({
  selector: 'app-deletedaccount-personel',
  templateUrl: './deletedaccount-personel.component.html',
  styleUrls: ['./deletedaccount-personel.component.css']
})
export class DeletedaccountPersonelComponent implements onInit{





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








}
