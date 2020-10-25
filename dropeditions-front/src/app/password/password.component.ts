import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {

  steps = 0;
  message = "";
  loading;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  onPasswordForgotten(data){
    this.loading = true;
    this.authService.passwordForgotten(data).subscribe(response => {
      this.steps = 1;
      this.loading = false;
    }, err => {
      this.message = err.error.message;
      this.loading = false;
      console.log(err);
    });
  }

  onPasswordChange(data){
    this.loading = true;
    this.authService.passwordChange(data).subscribe(response => {
      this.steps = 2;
      this.loading = false;
    }, err => {
      this.message = err.error.message;
      this.loading = false;
      console.log(err);
    });
  }

}
