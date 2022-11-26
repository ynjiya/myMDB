import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {GLOBAL} from "../../constant/GLOBAL";
import {Location} from "@angular/common";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchstr: any;
  movies: any[] = [];
  userInfo: any;
  searchpar: any;
  page: any;
  genre: any;
  orderby: any;
  sort: any;
  curlov = this.location.path().split('/')[1];

  constructor(private httpService: MovieServiceService,
              private route: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private location: Location) {
    this.route.paramMap.subscribe(result => {
      this.searchstr = result.get('searchstr');
      this.searchpar = result.get('searchpar');
      this.page = result.get('page');
      this.genre = result.get('genre');
      this.orderby = result.get('orderby');
      this.sort = result.get('sort');
    });
  }


  ngOnInit(): void {
    // @ts-ignore
    this.userInfo = JSON.parse(localStorage.getItem('user'));

    this.httpService.search(this.searchpar, this.searchstr, this.page, this.genre, this.orderby, this.sort).subscribe(result => {
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
