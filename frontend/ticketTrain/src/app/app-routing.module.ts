import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {SearchTicketsComponent} from "./search-tickets/search-tickets.component";
import {TicketsComponent} from "./tickets/tickets.component";
import {PaidTicketsComponent} from "./paid-tickets/paid-tickets.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'search-tickets', component: SearchTicketsComponent},
  {path: 'tickets', component: TicketsComponent},
  {path: 'paid-tickets', component: PaidTicketsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
