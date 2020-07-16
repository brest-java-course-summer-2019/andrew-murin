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

    if (!this.route.snapshot.queryParamMap.get('ticketDate') === null) {

      this.ticketCost = Number(this.route.snapshot.queryParamMap.get('ticketCost'));
      this.ticketDate = new Date(this.route.snapshot.queryParamMap.get('ticketDate'));
      this.cityFrom = this.route.snapshot.queryParamMap.get('cityFrom') as City;
      this.cityTo = this.route.snapshot.queryParamMap.get('cityTo') as City;


      let ticket = new Ticket(this.ticketCost,
        this.ticketDate.toISOString(),
        this.cityFrom,
        this.cityTo
      );


      ticketService.addTicket(ticket)
    }
  }


  ngOnInit(): void {
  }



}
