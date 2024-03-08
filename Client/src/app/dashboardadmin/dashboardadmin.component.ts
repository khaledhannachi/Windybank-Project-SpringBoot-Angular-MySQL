import { Component } from '@angular/core';
import {UserService} from "../services/user.service";
import {PersonelService} from "../services/personel.service";
import {ProfessionalService} from "../services/professional.service";
import {BusinessService} from "../services/business.service";
import {BankService} from "../services/bank.service";
import {AuthService} from "../services/auth.service";


interface onInit {
}

@Component({
  selector: 'app-dashboardadmin',
  templateUrl: './dashboardadmin.component.html',
  styleUrls: ['./dashboardadmin.component.css']
})
export class DashboardadminComponent  implements onInit{

  constructor(private userservice:UserService,private servicePersonal:PersonelService,
              private professionalService:ProfessionalService,
              private businessService:BusinessService,
             ) {

  }

  public  keyword:string="";
users:Array<any>=[];
personals:Array<any>=[];
  professionals:Array<any>=[];
  allbusiness:Array<any>=[];
ngOnInit() {
  this.userservice.getUsers().subscribe({
    next: data => {
      this.users = data;
    },
    error: error => {
      console.error('Error fetching data:', error);
    }
  });
  this.servicePersonal.getPersonnel().subscribe({
    next: data => {
      this.personals = data;
    },
    error: error => {
      console.error('Error fetching data:', error);
    }
  });
  this.professionalService.getProfessional().subscribe({
    next: data => {
      this.professionals = data;
    },
    error: error => {
      console.error('Error fetching data:', error);
    }
  });
  this.businessService.getBusiness().subscribe({
    next: data => {
      this.allbusiness = data;
    },
    error: error => {
      console.error('Error fetching data:', error);
    }
  });


}


  searchUsers(keyword:string) {

    this.userservice.searchUser(keyword).subscribe({
      next: (value: any[]) => {
        this.users = value;
      },
      error: (error: any) => {
        console.error('Error fetching data:', error);
      }
    })


  }
}
