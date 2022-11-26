import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../../movie-service.service";
import {MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {GLOBAL} from "../../../constant/GLOBAL";
import {Emitter} from "../../../shared/component/header/emitter";

@Component({
  selector: 'app-register',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  userInfo: any;
  loggedin = false;
  localStorageIsFree = true;
  data: any;
  constructor(private httpService: MovieServiceService,
              private messageService: MessageService,
              private router: Router) { }

  ngOnInit(): void {
    Emitter.authEmitter.subscribe(
        (auth: boolean) => {
          this.loggedin = auth;
        });

    // @ts-ignore
    this.userInfo = JSON.parse(localStorage.getItem('user'));
    console.log(this.userInfo)
    this.localStorageIsFree = this.userInfo == null;

    this.initData()
  }

  initData() {
    this.data = this.userInfo;
    for (let i=0; i<this.data.reviewList.length; i++) {
      this.data.reviewList[i] = {reviewid: this.data.reviewList[i]};
    }
    for (let i=0; i<this.data.watchlistList.length; i++) {
      this.data.watchlistList[i] = {wlid: this.data.watchlistList[i]};
    }
  }

  toResetPassword() {
    this.router.navigate(['reset_password'], {skipLocationChange: false});
  }

  logout() {
    Emitter.authEmitter.emit(false);
    localStorage.clear();
    this.localStorageIsFree = true;
    delete this.userInfo;
    this.router.navigate(['login/'], {skipLocationChange: false});
  }

  save_changes(){
    if (this.data.username == '') {
      GLOBAL.showWarning("Username is empty", this.messageService);
      return;
    }

    if (this.data.firstname == '') {
      GLOBAL.showWarning("Firstname is empty", this.messageService);
      return;
    }

    if (this.data.lastname == '') {
      GLOBAL.showWarning("Lastname is empty", this.messageService);
      return;
    }

    if (this.data.email == '') {
      GLOBAL.showWarning("Email is empty", this.messageService);
      return;
    }

    this.httpService.profileSettings(this.data, this.userInfo.userid).subscribe( result => {
      GLOBAL.showSuccess("Saved changes successfully", this.messageService);
      console.log(this.data);
      setTimeout(() => {
        // this.router.navigate(['home/'], {skipLocationChange: false});
        this.logout();
      },1000);
    },
      error => {
      console.log(this.data);
        GLOBAL.showWarning('Error saving changes', this.messageService);
      });
  }
}
