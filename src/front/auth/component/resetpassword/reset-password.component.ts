import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {MovieServiceService} from "../../../../movie-service.service";
import {MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {Emitter} from "../../../shared/component/header/emitter";
import {GLOBAL} from "../../../constant/GLOBAL";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  userInfo: any;
  loggedin = false;
  localStorageIsFree = true;
  userid: any;
  old_password: any;
  new_password: any;
  userpassword: any;

  data: any;
  constructor(private httpService: MovieServiceService,
              private messageService: MessageService,
              private router: Router) { }

  ngOnInit(): void {
    Emitter.authEmitter.subscribe(
        (auth: boolean) => {
          this.loggedin = auth;
        }
    )

    // @ts-ignore
    this.userInfo = JSON.parse(localStorage.getItem('user'));
    console.log(this.userInfo)
    this.localStorageIsFree = this.userInfo == null;

    for (let i=0; i<this.userInfo.reviewList.length; i++) {
      this.userInfo.reviewList[i] = {reviewid: this.userInfo.reviewList[i]};
    }
    for (let i=0; i<this.userInfo.watchlistList.length; i++) {
      this.userInfo.watchlistList[i] = {wlid: this.userInfo.watchlistList[i]};
    }

  }


  reset_password() {
    if (this.old_password == '') {
      GLOBAL.showWarning("Current password field is empty", this.messageService);
      return;
    }

    if (this.new_password == '') {
      GLOBAL.showWarning("New password field is empty", this.messageService);
      return;
    }

    this.userpassword = {"newPassword": this.new_password, "oldPassword": this.old_password};
    console.log(this.userpassword);

    this.httpService.changePassword(this.userpassword, this.userInfo.userid).subscribe(result => {
          GLOBAL.showSuccess("Changed password successfully", this.messageService);
          setTimeout(() => {
            this.router.navigate(['login/'], {skipLocationChange: false});
          },1000);
        },
        error => {
          GLOBAL.showWarning('Error occurred', this.messageService);
        })


  }
}
