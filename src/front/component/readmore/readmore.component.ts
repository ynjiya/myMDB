import { Component, OnInit } from '@angular/core';
import {MovieServiceService} from "../../../movie-service.service";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {forkJoin, Observable} from "rxjs";
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {MenuItem, MessageService} from "primeng/api";
import {GLOBAL} from "../../constant/GLOBAL";
import {formatDate} from "@angular/common";
import {MatTreeModule} from '@angular/material/tree';
import {NestedTreeControl, NestedTreeControlOptions} from '@angular/cdk/tree';
import {MatTreeNestedDataSource, MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import {MatCardModule} from '@angular/material/card';


interface RCNode {
  reviewid: string;
  movieid: number;
  userid: number;
  content: string;
  rating: number;
  date_added: string;
  children?: RCNode[];
}

/** Flat to-do item node with expandable and level information */
interface RCFlatNode {
  reviewid: string;
  movieid: number;
  userid: number;
  content: string;
  rating: number;
  date_added: string;
  level: number;
  expandable: boolean;
}


@Component({
  selector: 'app-readmore',
  templateUrl: './readmore.component.html',
  styleUrls: ['./readmore.component.css']
})
export class ReadmoreComponent implements OnInit {
  data: any;
  director: any;
  credits: any;
  movieId: any;
  userid: any;
  cast: any[] = [];
  crew: any[] = [];
  videos: any;
  similars: any[] = [];
  othersreviews: any[] = [];
  othersreviews0: any[] = []
  allReviews: any[] = [];
  responsiveOptions: any;
  val1: number = 5;
  stars: number = 1;
  review: any;
  rating: number = 0;
  parentid: number = 0
  userInfo: any;
  localStorageIsFree: boolean = false;
  tmp: any;
  // replyIsClicked = false;
  genres: any;
  runtime: any;

  treeControl = new NestedTreeControl<RCNode>(node => node.children);
  dataSource = new MatTreeNestedDataSource<RCNode>();

  constructor(private httpService: MovieServiceService,
              private route: ActivatedRoute,
              private sanitizer: DomSanitizer,
              private router: Router,
              private messageService: MessageService) {

    this.route.params.subscribe(result => {
      console.log(result);
      this.movieId = result.movieId;
      console.log(this.movieId);
    });
    this.
      responsiveOptions = [
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

    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.initData();

    let getOne = this.httpService.getOne(this.movieId);
    let getCredits = this.httpService.getCredits(this.movieId);
    let getVideos = this.httpService.getVideos(this.movieId);
    let getSimilar = this.httpService.getSimilar(this.movieId);

    forkJoin([getOne, getCredits, getVideos, getSimilar]).subscribe(results => {
      // this.data = results[0][0];
      this.data = results[0];
      console.log(this.data);
      this.data.poster_path = 'https://image.tmdb.org/t/p/w500' + this.data.poster_path;
      this.credits = results[1].crew;
      this.cast = results[1].cast;
      this.videos = results[2].results;
      this.similars = results[3].results;
      // this.director = results[4];

      // this.genres = results[4]
      this.director = this.data.director;

      this.runtime = Math.floor(this.data.runtime / 60).toString() + 'h' + (this.data.runtime % 60).toString() + 'm';

      this.videos.forEach((it: { videoUrl: SafeResourceUrl; key: string; }) => {
        it.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/' + it.key);
      });

      this.similars.forEach( it => {
        it.poster_path = 'https://image.tmdb.org/t/p/w500' + it.poster_path;
        this.val1 = Math.floor(it.vote_average / 2);
      })

      this.getOthersReviews();

    });
  }

  getUsername(userid: number) {
    this.httpService.getByUserid(userid).subscribe(r1 => {this.tmp = r1});
    return this.tmp.username.toString();
  }

  recurr(arr: any[]) {
    for (let i=0; i<arr.length; i++) {
      arr[i].replyIsClicked = false;

      if (arr[i].children) {
        this.recurr(arr[i].children);
      }
    }
  }

  getOthersReviews() {
    this.httpService.getOthersReviews(this.movieId).subscribe( result => {
      this.othersreviews = result;
      console.log(this.othersreviews);

      for (let i=0; i<this.othersreviews.length; i++) {
        if (Array.isArray(this.othersreviews[i])) {
          let tmpar = this.othersreviews[i]
          for (let j=0; j<tmpar.length; j++) {
            this.othersreviews.push(this.othersreviews[i][j])
          }
          this.othersreviews.splice(i, 1)
        }
      }
/*
      // this.othersreviews.forEach(it => {
      for (let i=0; i<this.othersreviews.length; i++) {
        // if (Array.isArray(this.othersreviews[i])) {
        //   console.log(this.othersreviews[i])
        //   this.othersreviews[i] = this.othersreviews[i][0];
        //   console.log(this.othersreviews[i])
        // }
        //   this.httpService.getByUserid(this.othersreviews[i].userid).subscribe(r1 => {this.othersreviews[i].users = r1});
        // this.othersreviews[i].users = this.getUsername(this.othersreviews[i].userid);
        console.log("this.othersreviews[i].users", this.othersreviews[i].users)
        this.othersreviews[i].replyIsClicked = false;

        if (this.othersreviews[i].children) {
          for (let val of this.othersreviews[i].children) {
            // this.httpService.getByUserid(val.userid).subscribe(r2 => {val.users = r2});
            val.replyIsClicked = false;

            if (val.children) {
              for (let val2 of val.children) {
                // this.httpService.getByUserid(val2.userid).subscribe(r3 => {val2.users = r3});
                val2.replyIsClicked = false;

                if (val2.children) {
                  for (let val3 of val2.children) {
                    // this.httpService.getByUserid(val3.userid).subscribe(r4 => {val3.users = r4});
                    val3.replyIsClicked = false;

                    if (val3.children) {
                      for (let val4 of val3.children) {
                        // this.httpService.getByUserid(val4.userid).subscribe(r5 => {val4.users = r5});
                        val4.replyIsClicked = false;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
*/
      // for (let val0 of this.othersreviews) {
      //   this.httpService.getByUserid(val0.userid).subscribe(r1 => {val0.users = r1});
      //   // val0.username = this.getUsername(val0.userid);
      //   val0.replyIsClicked = false;
      //   console.log(val0.username);
      //
      //
      //   if (val0.children) {
      //     for (let val of val0.children) {
      //       this.httpService.getByUserid(val.userid).subscribe(r2 => {val.users = r2});
      //       // val.username = this.getUsername(val.userid);
      //       val.replyIsClicked = false;
      //
      //       if (val.children) {
      //         for (let val2 of val.children) {
      //           this.httpService.getByUserid(val2.userid).subscribe(r3 => {val2.users = r3});
      //           // val2.username = this.getUsername(val2.userid);
      //           val2.replyIsClicked = false;
      //         }
      //       }
      //     }
      //   }
      // }

      this.recurr(this.othersreviews);

      console.log(this.othersreviews0);
      console.log(this.othersreviews);

      // for (let i=this.othersreviews.length; i>-1; i--) {
      //   this.othersreviews0.push(this.othersreviews[i]);
      // }

      for (let i=this.othersreviews.length - 1; i>-1; i--) {
        console.log(this.othersreviews[i].reviewid)
        if (this.othersreviews0.includes(Number(this.othersreviews[i].reviewid))) {
          this.othersreviews.splice(i, 1);
          console.log("after splice", i);
          console.log(this.othersreviews);
        }
        this.othersreviews0.push(Number(this.othersreviews[i].reviewid));
      }
      console.log(this.othersreviews0);
      console.log(this.othersreviews);


      this.dataSource.data = this.othersreviews;
      console.log(this.dataSource.data);
    })
  }

  readMore(id: number) {
    this.router.navigate(['readmore/' + id]);
  }

  initData() {
    this.review = {
      // reviewid: 0,
      parentid: 0,
      movie: {movieid: 0},
      users: {userid: 0},
      content: '',
      rating: 0,
      date_added: formatDate(Date.now(),'yyyy-MM-dd', 'en_US')
    }
  }

  addReview(pid: any) {
    if (this.review.content == '') {
      GLOBAL.showWarning("Review textarea is empty", this.messageService);
      return;
    }

    // @ts-ignore
    this.userInfo = JSON.parse(localStorage.getItem('user'));
    this.localStorageIsFree = this.userInfo == null;

    if (this.localStorageIsFree) {
      GLOBAL.showWarning("You are not logged id", this.messageService);
      setTimeout(() => {
        this.router.navigate(['login/'], {skipLocationChange: false});
      },1000)
      return;
    }

    this.review.movie = parseInt(this.movieId);
    this.review.rating = !this.rating ? null : this.rating;
    this.review.users = this.userInfo.userid;
    this.review.parentid = pid;

    this.httpService.addReview(this.review).subscribe( result => {
      GLOBAL.showSuccess("Uploaded successfully", this.messageService);
      this.getOthersReviews();
      setTimeout(() => {
        window.location.reload();
      },1000);
    });
    // this.getOthersReviews();
  }

  clearFields() {
    // @ts-ignore
    // document.getElementById('saveReview').value=null;
    this.review.content = '';
    // this.review.reviewid = 0;
    this.review.parentid = 0;
    this.review.rating = 0;
  }
  //
  // addToWatchlist() {
  //   // @ts-ignore
  //   this.userInfo = JSON.parse(localStorage.getItem('user'));
  //   GLOBAL.addToWatchlist(this.data, this.userInfo, this.httpService , this.router, this.messageService)
  // }

  addToWatchlist(movie: any) {
    // @ts-ignore
    this.userInfo = JSON.parse(localStorage.getItem('user'));
    GLOBAL.addToWatchlist(movie, this.userInfo, this.httpService , this.router, this.messageService)
  }

  // // FIX
  // addToWatchlist() {
  //
  //   // @ts-ignore
  //   this.userInfo = JSON.parse(localStorage.getItem('user'));
  //   this.localStorageIsFree = this.userInfo == null;
  //
  //   if (this.localStorageIsFree) {
  //     GLOBAL.showWarning("You are not logged id", this.messageService);
  //     setTimeout(() => {
  //       this.router.navigate(['login/'], {skipLocationChange: false});
  //     },1000)
  //     return;
  //   }
  //
  //   this.review.movieId = parseInt(this.movieId);
  //   this.review.rating = this.rating;
  //   this.review.userid = this.userInfo.userid;
  //   this.review.userName = this.userInfo.userName;
  //
  //   this.httpService.addReview(this.review).subscribe( result => {
  //     GLOBAL.showSuccess("Амжилттай хадгаллаа", this.messageService);
  //     this.getOthersReviews();
  //   });
  // }
  // hasChild = (_: number, node: RCNode) => !!node.children && node.children.length > 0;
  hasChild = (_: number, node: RCNode) => !!node.children;
  hasNoContent = (_: number, node: RCNode) => !node.content;


  addNewItem(parentid: number, node: RCNode) {
    this.addReview(parentid);
    this.treeControl.expand(node);
  }

  replyOnClick(review: any) {
    review.replyIsClicked = !review.replyIsClicked;
  }
}
