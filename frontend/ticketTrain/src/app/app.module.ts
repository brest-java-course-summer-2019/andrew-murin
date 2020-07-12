import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import { SearchTicketsComponent } from './components/search-tickets/search-tickets.component';
import { TicketsComponent } from './components/tickets/tickets.component';
import { PaidTicketsComponent } from './components/paid-tickets/paid-tickets.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { PaidTicketComponent } from './components/paid-ticket/paid-ticket.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { PayDialogComponent } from './components/dialogs/pay-dialog/pay-dialog.component';
import { DeleteDialogComponent } from './components/dialogs/delete-dialog/delete-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchTicketsComponent,
    TicketsComponent,
    PaidTicketsComponent,
    TicketComponent,
    PaidTicketComponent,
    PayDialogComponent,
    DeleteDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
