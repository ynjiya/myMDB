import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GLOBAL} from "../../constant/GLOBAL";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  userInfo: any;
  watchlist: any[] = [];
  page: any;
  genre: any;
  orderby: any;
  sort: any;

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

    console.log("userid:     ", this.userInfo.userid);


    this.httpService.getWatchlist(this.userInfo.userid, this.page, this.genre, this.orderby, this.sort).subscribe(result => {
        this.watchlist = result;
        console.log("watchlist:     ", this.watchlist);

        let imageUrl = 'https://image.tmdb.org/t/p/w500';

        this.watchlist.forEach((it: { poster_path: string; }) => {
          it.poster_path = imageUrl + it.poster_path;
        })
    },
      error => {
      GLOBAL.showWarning("asdf", this.messageService);
      });

  }

  readMore(id: number) {
    this.router.navigate(['readmore/' + id], {skipLocationChange: false});
  }

  deleteFromWatchlist(userid: number, movieid: number) {
    GLOBAL.deleteFromWatchlist(userid, movieid, this.httpService , this.router, this.messageService);
    window.location.reload();
  }
}
