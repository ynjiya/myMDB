import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {MovieServiceService} from "../../../movie-service.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username: any;

  constructor(private httpService: MovieServiceService,
              private http: HttpClient,
              private router: Router) { }

  ngOnInit(): void {
    // let userInfo = localStorage.getItem('userInfo');
  }
}
