import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {ThemeSwitchComponent} from './components/theme-switch/theme-switch.component';
import {RegisterComponent} from './components/register/register.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {HomeComponent} from './components/home/home.component';
import {ProfileComponent} from './components/profile/profile.component';
import {JwtInterceptor} from "./interceptors/jwt.interceptor";
import { SettingsComponent } from './components/settings/settings.component';
import { AlertComponent } from './components/alert/alert.component';
import { RealisationComponent } from './components/realisation/realisation.component';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    LoginComponent,
    ThemeSwitchComponent,
    RegisterComponent,
    HomeComponent,
    HomeComponent,
    ProfileComponent,
    SettingsComponent,
    AlertComponent,
    RealisationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }]
})
export class AppModule {
}
