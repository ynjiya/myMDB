import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {GLOBAL} from "../../constant/GLOBAL";
import {Location} from "@angular/common";

@Component({
  selector: 'app-upcoming',
  templateUrl: './upcoming.component.html',
  styleUrls: ['./upcoming.component.css']
})
export class UpcomingComponent implements OnInit {

  page: any;
  genre: any;
  orderby: any;
  sort: any;
  movies: any[] = [];
  userInfo: any;
  currentLocation = this.location.path().split('/')[1];



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

    this.httpService.getUpcoming(this.page, this.genre, this.orderby, this.sort).subscribe(result => {
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
