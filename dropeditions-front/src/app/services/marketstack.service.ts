import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
@Injectable({
  providedIn: 'root'
})
export class MarketstackService {

  constructor(private http: HttpClient, private appConfig: AppConfigService) { }

  getClosingPrice (day, symbols) {

    const fullpath = this.appConfig.apiUrl + "/" + day + "?symbols=" + symbols +  "&access_key=" + this.appConfig.apiKey;
    return this.http.get(fullpath);

  }
}
