import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { ButtonModule} from "primeng/button";
import { TabMenuModule} from "primeng/tabmenu";
import { InputTextModule} from "primeng/inputtext";
import { MenubarModule} from "primeng/menubar";
import { CarouselModule} from "primeng/carousel";
import { HttpClient, HttpClientModule, HttpHandler} from "@angular/common/http";
import { MoviesComponent } from './component/movies/movies.component';
import { CardModule} from "primeng/card";
import { MovieServiceService} from "../movie-service.service";
import { RatingModule} from "primeng/rating";
import { FormsModule} from "@angular/forms";
import { ReadmoreComponent } from './component/readmore/readmore.component';
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {DividerModule} from "primeng/divider";
import {RippleModule} from "primeng/ripple";
import {ScrollPanelModule} from "primeng/scrollpanel";
import {StyleClassModule} from "primeng/styleclass";
import {PanelModule} from "primeng/panel";
import {MenuModule} from "primeng/menu";
import {InputTextareaModule} from "primeng/inputtextarea";
import {AuthModule} from "./auth/auth.module";
import {SharedModule} from "./shared/shared.module";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {HeaderComponent} from "./shared/component/header/header.component";
import {LoginComponent} from "./auth/component/login/login.component";
import { PopularComponent } from './component/popular/popular.component';
import { WatchlistComponent } from './component/watchlist/watchlist.component';
import { UpcomingComponent } from './component/upcoming/upcoming.component';
import { TopratedComponent } from './component/toprated/toprated.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTreeModule } from '@angular/material/tree';
import { NestedTreeControl } from '@angular/cdk/tree';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatDividerModule} from "@angular/material/divider";
import {SearchComponent} from "./component/search/search.component";
import {AllComponent} from "./component/all/all.component";


// @ts-ignore
// @ts-ignore
// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MoviesComponent,
    ReadmoreComponent,
    PopularComponent,
    WatchlistComponent,
    UpcomingComponent,
    TopratedComponent,
    SearchComponent,
    AllComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    TabMenuModule,
    InputTextModule,
    MenubarModule,
    CarouselModule,
    CardModule,
    HttpClientModule,
    RatingModule,
    FormsModule,
    NzLayoutModule,
    DividerModule,
    RippleModule,
    ScrollPanelModule,
    StyleClassModule,
    PanelModule,
    MenuModule,
    InputTextareaModule,
    AuthModule,
    SharedModule,
    BrowserAnimationsModule,
    MatTreeModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatDividerModule,
    // NestedTreeControl
  ],
  exports : [
    HomeComponent,
    AuthModule
  ],
  providers: [HttpClient, MovieServiceService, HomeComponent, AuthModule, MessageService, HeaderComponent, LoginComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
