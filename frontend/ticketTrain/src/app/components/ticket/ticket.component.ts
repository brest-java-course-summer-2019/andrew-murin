import { Component, OnInit } from '@angular/core';
import {Ticket} from "../../model/Ticket";
import {City} from "../../model/City";
import {TicketService} from "../../services/ticketService/ticket.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  ticketCost: number;
  ticketDate: Date;
  cityFrom: City;
  cityTo: City;

  constructor(private ticketService: TicketService,
              private route: ActivatedRoute) {




    this.ticketCost = Number(this.route.snapshot.queryParamMap.get('ticketCost'));
    this.ticketDate = new Date(this.route.snapshot.queryParamMap.get('ticketDate'));
    this.cityFrom = this.route.snapshot.queryParamMap.get('cityFrom') as City;
    this.cityTo = this.route.snapshot.queryParamMap.get('cityTo') as City;

    // C


    let ticket = new Ticket(this.ticketCost,
                        this.ticketDate.toUTCString(),
                        this.cityFrom,
                        this.cityTo);

    console.log("tickets: " + ticket);
    console.log("tickets: " + this.ticketDate + " " + this.ticketCost + " " + this.cityFrom + " " + this.cityTo);

    // ticketService.addTicket(ticket)
  }


  ngOnInit(): void {
  }


  onAddTicket(fromCity: string, toCity: string, ticketCost: number, ticketDate: string) {
    // let ticket = new Ticket(ticketCost, new Date(ticketDate), City[fromCity], City[toCity])
    // console.log(ticket);

    // this.ticketService.addTicket(ticket);
  }



}
