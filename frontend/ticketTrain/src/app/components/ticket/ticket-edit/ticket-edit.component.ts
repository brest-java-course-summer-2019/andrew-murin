import {Component, OnInit} from '@angular/core';
import {Ticket} from "../../../model/Ticket";
import {TicketService} from "../../../services/ticketService/ticket.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-ticket-edit',
  templateUrl: './ticket-edit.component.html',
  styleUrls: ['./ticket-edit.component.css']
})
export class TicketEditComponent implements OnInit {

  ticketId: string;
  ticket: Ticket;


  constructor (private ticketService: TicketService,
               private route: ActivatedRoute) {

  };


  ngOnInit(): void {

    this.ticketId = this.route.snapshot.queryParamMap.get('ticketId');

    this.ticketService.findById(this.ticketId).subscribe(ticket => this.ticket = ticket)
  }


  onEditFormSubmit(ticket: Ticket) {
    this.ticket.fromCity = ticket.fromCity;
    this.ticket.toCity = ticket.toCity;
    this.ticket.ticketDate = new Date(ticket.ticketDate).toISOString();
    this.ticket.ticketCost = ticket.ticketCost;

    this.ticketService.editTicket(this.ticket);

  }

}
