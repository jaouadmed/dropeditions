import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { Section1Component } from './section1/section1.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'section1', component: Section1Component},
  {path: 'section2', component: LoginComponent},
  {path: 'section3', component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
