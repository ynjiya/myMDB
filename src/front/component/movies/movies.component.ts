import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {Router} from "@angular/router";
import {GLOBAL} from "../../constant/GLOBAL";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  popular: any[] = [];
  upcoming: any[] = [];
  toprated: any[] = [];
  // trending: any[] = [];

  val1: number = 5;
  stars: number = 5;
  responsiveOptions: any;
  page: number = 1;
  genre: number = 0;
  orderby: string = 'vote';
  sort: string = 'desc';
  userInfo: any;
  localStorageIsFree: boolean = false;

  watchListMovie: any;


  constructor(private httpService: MovieServiceService,
              private router: Router,
              private messageService: MessageService) {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 3,
        numScroll: 3
      },
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1
      }
    ];
  }

  ngOnInit(): void {
    this.userInfo = JSON.parse(localStorage.getItem('user'));

    this.httpService.getPopular(this.page, this.genre, 'popularity', "desc").subscribe(result => {
      this.popular = result;
      console.log(this.popular);
      let imageUrl = 'https://image.tmdb.org/t/p/w500';

      this.popular.forEach(it => {
        it.poster_path = imageUrl + it.poster_path;
        this.val1 = Math.floor(it.vote_average / 2);
      })
    });

    this.httpService.getUpcoming(this.page, this.genre, "date", "desc").subscribe(result => {
      this.upcoming = result;
      let imageUrl = 'https://image.tmdb.org/t/p/w500';

      this.upcoming.forEach(it => {
        it.poster_path = imageUrl + it.poster_path;
        this.val1 = Math.floor(it.vote_average / 2);
      })
    });

    this.httpService.getTopRated(this.page, this.genre, "date", "asc").subscribe(result => {
      this.toprated = result;
      let imageUrl = 'https://image.tmdb.org/t/p/w500';

      this.toprated.forEach(it => {
        it.poster_path = imageUrl + it.poster_path;
        this.val1 = Math.floor(it.vote_average / 2);
      })
    });
  }

  readMore(movieId: number) {
    this.router.navigate(['readmore/' + movieId], {skipLocationChange: false});
  }

  addToWatchlist(movie: any) {
      GLOBAL.addToWatchlist(movie, this.userInfo, this.httpService, this.router, this.messageService)
  }

  toPopular() {
    this.router.navigate(['popular/1/'], {skipLocationChange: false});
  }

  toUpcoming() {
    this.router.navigate(['upcoming/1/0/date/desc'], {skipLocationChange: false});
  }

  toToprated() {
    this.router.navigate(['toprated/1/0/date/asc'], {skipLocationChange: false});
  }

}
