import {Component, OnInit, Output} from '@angular/core';
import {Ticket} from "../../model/Ticket";
import {TicketService} from "../../services/ticketService/ticket.service";

@Component({
  selector: 'app-search-tickets',
  templateUrl: './search-tickets.component.html',
  styleUrls: ['./search-tickets.component.css']
})
export class SearchTicketsComponent implements OnInit {

  @Output()
  searchedTickets: Ticket[];

  constructor(private ticketService: TicketService) { }

  ngOnInit(): void {
  }

  onSearchTickets() {

  }


}
