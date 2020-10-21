import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  host2 = 'http://localhost:8080';
  jwt;
  username;
  roles: Array<string>;
  apikey;

  constructor(private http: HttpClient) { }

  login(data) {
    console.log(data);
    return this.http.post(this.host2 + '/login', data, {observe: 'response'});
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
    this.apikey = objJWT.apikey;
  }

  isUser() {
    return this.roles.indexOf('USER') >= 0;
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

  getApiKey(){
    return this.apikey;
  }
}
