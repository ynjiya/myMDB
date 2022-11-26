import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {MessageService} from "primeng/api";
import {MovieServiceService} from "../../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Emitter} from "../../../shared/component/header/emitter";
import {HeaderComponent} from "../../../shared/component/header/header.component";
import {GLOBAL} from "../../../constant/GLOBAL";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  data: any;
  localStorageIsFree: any;
  userInfo: any;
  prevUrl: any;
  localUrl: any;


  constructor(private messageService: MessageService,
              private httpService: MovieServiceService,
              private router: Router,
              private http: HttpClient,
              private header: HeaderComponent,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.data = {
      username :'',
      password: ''
    }
  }

  login() {
    if (this.data.username == '') {
      GLOBAL.showWarning("Username is empty", this.messageService);
      return;
    }

    if (this.data.password == '') {
      GLOBAL.showWarning("Password is empty", this.messageService);
      return;
    }

    console.log("ene odoo yuuuvee");
    console.log(this.data);

    this.httpService.getOneUser(this.data).subscribe(
      data => {
        Emitter.authEmitter.emit(true);

        this.header.loggedin = true;
        localStorage.setItem('user', JSON.stringify(data));

        GLOBAL.showSuccess("Logged in", this.messageService);


        setTimeout(() => {
          this.router.navigate(['home/'], {skipLocationChange: false}).then(() => {
            window.location.reload();});
        },1000);
      },
      error => {
        Emitter.authEmitter.emit(false);
        GLOBAL.showWarning('Incorrect username or password', this.messageService);

      });
  }

  register() {
    this.router.navigate(['register/'], {skipLocationChange: false});
  }
}
