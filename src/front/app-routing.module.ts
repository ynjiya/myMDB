import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { ReadmoreComponent} from "./component/readmore/readmore.component";
import {PopularComponent} from "./component/popular/popular.component";
import {WatchlistComponent} from "./component/watchlist/watchlist.component";
import {UpcomingComponent} from "./component/upcoming/upcoming.component";
import {TopratedComponent} from "./component/toprated/toprated.component";
import {SearchComponent} from "./component/search/search.component";
import {AllComponent} from "./component/all/all.component";
import {SettingsComponent} from "./auth/component/settings/settings.component";
import {MoviesComponent} from "./component/movies/movies.component";



const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: MoviesComponent},
  { path: '', component: MoviesComponent},
  { path: 'readmore/:movieId', component: ReadmoreComponent},
  { path: 'popular/:page/:genre/:orderby/:sort', component: PopularComponent},
  { path: 'upcoming/:page/:genre/:orderby/:sort', component: UpcomingComponent},
  { path: 'toprated/:page/:genre/:orderby/:sort', component: TopratedComponent},
  { path: 'watchlist/:page/:genre/:orderby/:sort', component: WatchlistComponent},
  { path: 'search/:searchpar/:searchstr/:page/:genre/:orderby/:sort', component: SearchComponent},
  { path: 'all/:page/:genre/:orderby/:sort', component: AllComponent},
  // { path: 'test', redirectTo: '', pathMatch: 'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
