import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {Emitter} from "./emitter";
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';


interface  Elem{
  orderby: string;
  sort: string;
}

interface Type {
  viewValue: string;
  value: Elem;
}

interface Sorder{
  title: string;
  type: Type[];
}

interface  Elemint{
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
    providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]

})
export class HeaderComponent implements OnInit {
  userInfo: any;
  loggedin = false;
  localStorageIsFree = true;
  searchstr: any;
  isFilterezable = true;
  currentLocation = this.location.path().split('/')[1];

  selectValuespar: any[] = ['title', 'director']
  selectedValuepar = this.selectValuespar[0];

  selectSorder: Sorder[] = [
    {title: 'Release date', type: [
        {viewValue: 'Earliest first', value: {orderby: 'date', sort: 'asc'}},
        {viewValue: 'Newest first', value: {orderby: 'date', sort: 'desc'}}]},
    {title: 'Average rating', type: [
        {viewValue: 'Lowest first', value: {orderby: 'vote', sort: 'asc'}},
        {viewValue: 'Highest first', value: {orderby: 'vote', sort: 'desc'}}]},
    {title: 'Popularity', type: [
        {viewValue: 'Least popular first', value: {orderby: 'popularity', sort: 'asc'}},
        {viewValue: 'Most popular first', value: {orderby: 'popularity', sort: 'desc'}}]},
    {title: 'Runtime', type: [
        {viewValue: 'Shortest first', value: {orderby: 'runtime', sort: 'asc'}},
        {viewValue: 'Longest first', value: {orderby: 'runtime', sort: 'desc'}}]}
  ];

  selectValuesgenre: Elemint[] = [
    {value: '28', viewValue: 'Action'},
    {value: '12', viewValue: 'Adventure'},
    {value: '16', viewValue: 'Animation'},
    {value: '35', viewValue: 'Comedy'},
    {value: '80', viewValue: 'Crime'},
    {value: '99', viewValue: 'Documentary'},
    {value: '18', viewValue: 'Drama'},
    {value: '10751', viewValue: 'Family'},
    {value: '14', viewValue: 'Fantasy'},
    {value: '36', viewValue: 'History'},
    {value: '27', viewValue: 'Horror'},
    {value: '10402', viewValue: 'Music'},
    {value: '9648', viewValue: 'Mystery'},
    {value: '10749', viewValue: 'Romance'},
    {value: '878', viewValue: 'Science Fiction'},
    {value: '10770', viewValue: 'TV Movie'},
    {value: '53', viewValue: 'Thriller'},
    {value: '10752', viewValue: 'War'},
    {value: '37', viewValue: 'Western'}];
  selectedValuegenre = 0;


  constructor(private router: Router,
              private route: ActivatedRoute,
              private location: Location) {
  }

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
    console.log('currentLocation: ' + this.currentLocation)


    if (this.currentLocation === 'home' || this.currentLocation === 'readmore'  || this.currentLocation === 'login'  || this.currentLocation === 'register' || this.currentLocation === 'settings' || this.currentLocation === 'reset_password' ) {
      this.isFilterezable = false;
    }
  }

  logout() {
    Emitter.authEmitter.emit(false);
    localStorage.clear();
    this.localStorageIsFree = true;
    delete this.userInfo;
    this.router.navigate(['login/'], {skipLocationChange: false});
  }

  toWatchlist() {
    this.router.navigate(['watchlist/1/0/date/desc'], {skipLocationChange: false});
  }

  toSettings() {
    this.router.navigate(['settings'], {skipLocationChange: false});
  }

  toModify(value: any) {
    let curpath = this.location.path().split('/');

    if (curpath[1] === 'search') {
      console.log(curpath);
      curpath = [curpath[0], curpath.slice(1, 4).join('/')].concat(curpath.slice(4));
      console.log(curpath);
    }
    // ['', 'upcoming', '2', '0', 'by_date', 'sort_asc']
    if (curpath[1] === 'all' || curpath[1] === 'upcoming' || curpath[1] === 'toprated' || curpath[1] === 'popular' || curpath[1] === 'myWatchlist') {
      console.log(curpath);
      curpath = [curpath[0], curpath.slice(1, 3).join('/')].concat(curpath.slice(3));
      console.log(curpath);
    }


    let plen = curpath.length;
    console.log(plen);

    console.log(this.router.parseUrl(this.location.path()));

    if (typeof value === 'object') {
      let selectedValueorder = value.orderby;
      let selectedValuesort = value.sort;

      if (plen <= 3) {
        this.router.navigate([this.location.prepareExternalUrl(curpath[1] + '/' +
            selectedValueorder + '/' + selectedValuesort)], {skipLocationChange: false});
      }
      else {
        console.log(curpath);
        curpath[plen-2] = selectedValueorder;
        curpath[plen-1] = selectedValuesort;
        console.log(curpath);
        this.router.navigate([curpath.join('/')])
            .then(() => {window.location.reload();});
      }
    }
    else {
      let selectedValuegenre = value;

      if (plen <= 3) {
        this.router.navigate([this.location.prepareExternalUrl(curpath[1] + '/' + selectedValuegenre)]);
      }
      if (curpath[1].indexOf('upcoming') || curpath[1].indexOf('toprated') || curpath[1].indexOf('all') || curpath[1].indexOf('watchlist')) {
        console.log(curpath);
        console.log(curpath[0]);
        this.router.navigate([curpath[1] + '/' + selectedValuegenre + '/' + curpath.slice(-2)[0] + '/' + curpath.slice(-2)[1]])
          .then(() => {window.location.reload();});
      }

      else {
        console.log(curpath);
        console.log(curpath[0]);
        this.router.navigate([curpath[1] + '/' + curpath[2] + '/' + selectedValuegenre + '/' + curpath.slice(-2)[0] + '/' + curpath.slice(-2)[1]])
            .then(() => {window.location.reload();});
      }
    }
  }


  toSearch(str: string) {
    this.router.navigate(['search/' + this.selectedValuepar + '/' + str + '/0/0/vote/desc'])
        .then(() => {window.location.reload()});
  }


}

