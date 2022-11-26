import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GLOBAL} from "../../constant/GLOBAL";
import {MessageService} from "primeng/api";
import {Location} from "@angular/common";

@Component({
  selector: 'app-popular',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.css']
})

export class AllComponent implements OnInit {

  page: any;
  movies: any[] = [];
  userInfo: any;
  genre: any;
  orderby: any;
  sort: any;

  constructor(private httpService: MovieServiceService,
              private route: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private location: Location) {
    this.route.paramMap.subscribe(result => {
      this.page = result.get('page');
      this.genre = result.get('genre');
      this.orderby = result.get('orderby');
      this.sort = result.get('sort');
    });
  }

  ngOnInit(): void {

    // @ts-ignore
    this.userInfo = JSON.parse(localStorage.getItem('user'));
    console.log(this.userInfo);


    this.httpService.getAll(this.page, this.genre, this.orderby, this.sort).subscribe(result => {
      this.movies = result;
      let imageUrl = 'https://image.tmdb.org/t/p/w500';
      console.log("params !!" + this.page, this.genre, this.orderby, this.sort)

      this.movies.forEach(it => {
        it.poster_path = imageUrl + it.poster_path;
      })
    });

  }

  readMore(id: number) {
    this.router.navigate(['readmore/' + id], {skipLocationChange: false});
  }

  addToWatchlist(movie: any) {
    GLOBAL.addToWatchlist(movie, this.userInfo, this.httpService , this.router, this.messageService)
  }

  toNext() {
    let curpath = this.location.path().split('/');
    curpath = curpath.slice(0, 2).concat((parseInt(curpath[2]) + 1).toString()).concat(curpath.slice(3));
    this.router.navigate(curpath)
        .then(() => {window.location.reload();});

  }
}
