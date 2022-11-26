import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './component/header/header.component';
import {MenubarModule} from "primeng/menubar";
import {RouterModule} from "@angular/router";
import {MenuModule} from "primeng/menu";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {MatSelectModule} from "@angular/material/select";
import {MatOptionModule} from "@angular/material/core";



@NgModule({
  declarations: [
    HeaderComponent
  ],
    imports: [
        CommonModule,
        MenubarModule,
        RouterModule,
        MenuModule,
        FormsModule,
        ButtonModule,
        MatSelectModule,
        MatOptionModule
    ],
  exports: [
    HeaderComponent
  ]
})
export class SharedModule { }
