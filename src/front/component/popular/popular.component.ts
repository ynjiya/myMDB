import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GLOBAL} from "../../constant/GLOBAL";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-popular',
  templateUrl: './popular.component.html',
  styleUrls: ['./popular.component.css']
})

export class PopularComponent implements OnInit {

  page: any;
  genre: any;
  orderby: any;
  sort: any;
  movies: any[] = [];
  userInfo: any;

  constructor(private httpService: MovieServiceService,
              private route: ActivatedRoute,
              private router: Router,
              private messageService: MessageService) {
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


    this.httpService.getPopular(this.page, this.genre, 'popularity', 'desc').subscribe(result => {
      this.movies = result;
      let imageUrl = 'https://image.tmdb.org/t/p/w500';

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

}
