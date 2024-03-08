import { Component } from '@angular/core';
import {ProfessionalService} from "../services/professional.service";

interface onInit {
}

@Component({
  selector: 'app-deletedaccount-professional',
  templateUrl: './deletedaccount-professional.component.html',
  styleUrls: ['./deletedaccount-professional.component.css']
})
export class DeletedaccountProfessionalComponent implements onInit{



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






}
