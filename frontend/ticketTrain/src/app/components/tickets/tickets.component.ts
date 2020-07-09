import {Component, Inject, OnInit} from '@angular/core';
import {TicketService} from "../../services/ticketService/ticket.service";
import {Ticket} from "../../model/Ticket";

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  tickets: Ticket[];

  constructor(
    private ticketService: TicketService
  ) { }

  ngOnInit(): void {
    this.ticketService.findAll().subscribe(tickets => {
      console.log(tickets)
      this.tickets = tickets});
  }

}
