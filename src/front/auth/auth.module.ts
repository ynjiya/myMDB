import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { ResetPasswordComponent } from './component/resetpassword/reset-password.component';
import {ToastModule} from "primeng/toast";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {KeyFilterModule} from "primeng/keyfilter";
import {SettingsComponent} from "./component/settings/settings.component";

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    ResetPasswordComponent,
      SettingsComponent
  ],
  exports: [
    LoginComponent,
    RegisterComponent,
    ResetPasswordComponent,
    SettingsComponent,
    ToastModule,
    BrowserAnimationsModule
  ],
    imports: [
        CommonModule,
        AuthRoutingModule,
        FormsModule,
        ToastModule,
        BrowserAnimationsModule,
        KeyFilterModule,
    ]
})
export class AuthModule { }
