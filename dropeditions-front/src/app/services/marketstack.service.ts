import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MarketstackService {

  path = 'http://api.marketstack.com/v1/eod';

  constructor(private http: HttpClient) { }

  getClosingPrice (day, symbols) {

    const fullpath = this.path + "/" + day + "?symbols=" + symbols +  "&access_key=c3e426520e2bf0a4e540e9b08b078033";
    return this.http.get(fullpath);

  }
}
