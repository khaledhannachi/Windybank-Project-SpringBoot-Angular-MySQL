import { Component } from '@angular/core';
import {BusinessService} from "../services/business.service";

interface onInit {
}

@Component({
  selector: 'app-deletedaccountbusiness',
  templateUrl: './deletedaccountbusiness.component.html',
  styleUrls: ['./deletedaccountbusiness.component.css']
})
export class DeletedaccountbusinessComponent implements onInit{



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




}
