import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {GLOBAL} from "../../constant/GLOBAL";

@Component({
  selector: 'app-toprated',
  templateUrl: './toprated.component.html',
  styleUrls: ['./toprated.component.css']
})
export class TopratedComponent implements OnInit {

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

    this.httpService.getTopRated(this.page, this.genre, this.orderby, this.sort).subscribe(result => {
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
    GLOBAL.addToWatchlist(movie, this.userInfo,this.httpService , this.router, this.messageService)
  }

}
