import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../../movie-service.service";
import {MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {GLOBAL} from "../../../constant/GLOBAL";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  data: any;
  constructor(private httpService: MovieServiceService,
              private messageService: MessageService,
              private router: Router) { }

  ngOnInit(): void {
    this.initData()
  }

  initData() {
    this.data = {
      username: '',
      firstname:  '',
      lastname: '',
      email: '',
      password: ''
    }
  }

  register(){
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

    if (this.data.password == '') {
      GLOBAL.showWarning("Password is empty", this.messageService);
      return;
    }

    this.httpService.addUser(this.data).subscribe( result => {
      GLOBAL.showSuccess("Registered successfully", this.messageService);
      console.log(this.data);
      setTimeout(() => {
        this.router.navigate(['login/'], {skipLocationChange: false});
      },1000);
    },
      error => {
        GLOBAL.showWarning('Already registered with this email', this.messageService);
      });
  }
}
