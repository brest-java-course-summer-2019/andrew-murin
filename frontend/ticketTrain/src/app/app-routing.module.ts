import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {SearchTicketsComponent} from "./components/search-tickets/search-tickets.component";
import {TicketsComponent} from "./components/tickets/tickets.component";
import {PaidTicketsComponent} from "./components/paid-tickets/paid-tickets.component";
import { TicketComponent } from './components/ticket/ticket.component';
import {PaidTicketComponent} from "./components/paid-ticket/paid-ticket.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'search-tickets', component: SearchTicketsComponent},
  {path: 'tickets', component: TicketsComponent},
  {path: 'tickets/:', component: TicketComponent},
  {path: 'paid-tickets', component: PaidTicketsComponent},
  {path: 'paid-tickets/:', component: PaidTicketComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
