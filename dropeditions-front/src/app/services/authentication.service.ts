import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  jwt;
  username;
  roles: Array<string>;

  constructor(private http: HttpClient, private appConfig: AppConfigService) { }

  login(data) {
    console.log(data);
    return this.http.post(this.appConfig.backendUrl + '/login', data, {observe: 'response'});
  }

  register(data) {
    console.log(data);
    return this.http.post(this.appConfig.backendUrl + '/register', data, {observe: 'response'});
  }

  passwordForgotten(data) {
    console.log(data);
    return this.http.post(this.appConfig.backendUrl + '/passwordForgotten', data, {observe: 'response'});
  }

  passwordChange(data) {
    console.log(data);
    return this.http.post(this.appConfig.backendUrl + '/passwordChange', data, {observe: 'response'});
  }

  saveToken(jwt) {
    localStorage.setItem('token', jwt);
    this.jwt = jwt;
    this.parseJWT();
  }

  parseJWT() {
    const jwtHelper = new JwtHelperService();
    const objJWT = jwtHelper.decodeToken(this.jwt);
    this.username = objJWT.obj;
    this.roles = objJWT.roles;
  }

  isUser() {
    return this.roles? this.roles.indexOf('USER') >= 0 : false;
  }


  isAuthenticated() {
    return this.roles && (this.isUser) && this.jwt;
  }

  loadToken() {
    this.jwt = localStorage.getItem('token');
    this.parseJWT();
  }

  logout() {
    localStorage.removeItem('token');
    this.initParams();
  }

  initParams() {
    this.jwt = undefined;
    this.username = undefined;
    this.roles = undefined;
  }
}
