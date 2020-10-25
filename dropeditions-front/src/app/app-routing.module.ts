import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PasswordComponent } from './password/password.component';
import { RegisterComponent } from './register/register.component';
import { Section1Component } from './section1/section1.component';
import { Section2Component } from './section2/section2.component';
import { Section3Component } from './section3/section3.component';
import { CanActivateService } from './services/can-activate.service';
 
const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'password', component: PasswordComponent},
  {path: 'section1', component: Section1Component, canActivate: [CanActivateService]},
  {path: 'section2', component: Section2Component, canActivate: [CanActivateService]},
  {path: 'section3', component: Section3Component, canActivate: [CanActivateService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
