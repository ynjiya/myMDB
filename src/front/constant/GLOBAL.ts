import {formatDate} from '@angular/common';


export class GLOBAL {

  public static showSuccess(message: string, messageService: any) {
      messageService.add({severity:'success', detail: message});
  }

  public static showWarning(message: string, messageService: any) {
      messageService.add({severity:'warn', detail: message});
  }

 public static addToWatchlist(movie: any, userInfo: any, httpService: any, router: any, messageService: any) {
   // @ts-ignore
    let localStorageIsFree : boolean= userInfo == null;

    if (localStorageIsFree) {
      this.showWarning("You are not logged in", messageService);
      setTimeout(() => {
        router.navigate(['login/'], {skipLocationChange: false});
      },1000)
      return;
    }
    
   let watchListMovie = {
     //  users: {userid : userInfo.userid},
     // movie: {movieid : movie.movieid},
     usersid: userInfo.userid,
     movieid: movie.movieid,
     date_added: formatDate(Date.now(),'yyyy-MM-dd', 'en_US')

     // userid: userInfo.userid,
     // movie: movie,
     // // date_added: Date()
     // date_added: formatDate(Date.now(),'yyyy-MM-dd', 'en_US')
   }

    httpService.addToWatchlist(watchListMovie).subscribe(() => {
        this.showSuccess("Added to your Watchlist!", messageService);
        console.log('watchListMovie:   ', watchListMovie);
      },
      () => {
        this.showWarning("This movie is already in your Watchlist", messageService);
        return;
      });
  }

    public static deleteFromWatchlist(userid: number, movieid: number, httpService: any, router: any, messageService: any) {
        httpService.deleteFromWatchlist(userid, movieid).subscribe(() => {
            console.log("in deleted deleteFromWatchlist");

            this.showSuccess("Deleted from your Watchlist!", messageService);
                console.log("deleted deleteFromWatchlist");
            },
        () => {
            this.showWarning("This movie is not in your Watchlist", messageService);
            return;
        });
    }
}
