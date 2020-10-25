import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { Section1Component } from './section1/section1.component';
import { Section2Component } from './section2/section2.component';
import { Section3Component } from './section3/section3.component';
import { AppConfigService } from './services/app-config.service';
import { RegisterComponent } from './register/register.component';
import { CanActivateService } from './services/can-activate.service'
import { PasswordComponent } from './password/password.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    Section1Component,
    Section2Component,
    Section3Component,
    RegisterComponent,
    PasswordComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, FormsModule,
    
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [AppConfigService],
      useFactory: (appConfigService: AppConfigService) => {
        return () => {
          return appConfigService.loadAppConfig();
        };
      }
    },
    CanActivateService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
