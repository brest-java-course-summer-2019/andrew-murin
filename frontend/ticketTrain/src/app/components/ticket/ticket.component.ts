import { Component, OnInit } from '@angular/core';
import {Ticket} from "../../model/Ticket";
import {City} from "../../model/City";
import {TicketService} from "../../services/ticketService/ticket.service";

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  constructor(private ticketService: TicketService) { }


  ngOnInit(): void {
  }


  onAddTicket(fromCity: string, toCity: string, ticketCost: number, ticketDate: string) {
    let ticket = new Ticket(ticketCost, new Date(ticketDate), City[fromCity], City[toCity])
    console.log(ticket);

    this.ticketService.addTicket(ticket);
  }

}
