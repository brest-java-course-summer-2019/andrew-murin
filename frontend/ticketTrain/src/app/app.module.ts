import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import { SearchTicketsComponent } from './search-tickets/search-tickets.component';
import { TicketsComponent } from './tickets/tickets.component';
import { PaidTicketsComponent } from './paid-tickets/paid-tickets.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchTicketsComponent,
    TicketsComponent,
    PaidTicketsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
