import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-request-moneyBusiness',
  templateUrl: './request-moneyBusiness.component.html',
  styleUrls: ['./request-moneyBusiness.component.css']
})
export class RequestMoneyBusinessComponent implements OnInit {
  constructor(private route: Router) {}

  ngOnInit(): void {
  }

  submit() {
    // Your form submission logic goes here...

    // Display confirmation dialog using SweetAlert2
    Swal.fire({
      title: 'Are you sure?',
      text: 'You want to perform this action.',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, do it!',

    }).then((result) => {
      if (result.isConfirmed) {
        // User clicked "Yes, do it!" button
        // Perform the action you want to confirm
        Swal.fire('Success!', 'Your action has been performed.', 'success');
        this.route.navigateByUrl("/dashboardBusiness")
      } else {
        // User clicked Cancel or closed the dialog
        Swal.fire('Cancelled', 'Your action has been cancelled.', 'info');
      }
    });
  }}
