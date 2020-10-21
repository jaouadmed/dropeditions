import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Dropeditions';
  username;

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.authService.loadToken();
    this.username = this.authService.username;
  }

  isUser() {
    return this.authService.isUser();
  }


  isAuthenticated() {
    this.username = this.authService.username;
    return this.authService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
  }
}
