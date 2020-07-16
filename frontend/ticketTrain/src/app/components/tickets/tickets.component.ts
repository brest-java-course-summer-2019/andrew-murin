import {Component, OnInit} from '@angular/core';
import {TicketService} from "../../services/ticketService/ticket.service";
import {Ticket} from "../../model/Ticket";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  tickets: Ticket[];
  displayPayModal = 'none';
  displayDeleteModal = 'none';
  ticket: Ticket;

  deleteTicket: string;

  constructor(private ticketService: TicketService,
              private route: ActivatedRoute,
              private router: Router) {

    const params = {
      'cityFrom': this.route.snapshot.queryParamMap.get('cityFrom'),
      'cityTo': this.route.snapshot.queryParamMap.get('cityTo'),
      'startDate': this.route.snapshot.queryParamMap.get('startDate'),
      'finishDate': this.route.snapshot.queryParamMap.get('finishDate')
    }

    ticketService.searchTicket(params).subscribe(tickets => {
      this.tickets = tickets
    })
  }

  ngOnInit(): void {

    this.ticketService.findAll().subscribe(tickets => {
      this.tickets = tickets
    });
  }


  openDialog(): void {
    this.displayPayModal = 'block';
  }

  findTicketAndSendToChild(ticketId: string): void {
    this.ticket = this.tickets.find(value => value.id === ticketId);
    this.ticketService.init(this.ticket);
  }

  receiveEventToClosePayDialog($event): void {
    this.displayPayModal = $event;
    // this.displayDeleteModal = $event;
  }

  receiveEventToCloseDeleteDialog($event): void {
    this.displayDeleteModal = $event;
    // this.displayDeleteModal = $event;
  }


  openDeleteDialog(): void {
    this.displayDeleteModal = 'block';
  }


  findAndDeleteTicket(id: string): void {
    this.ticketService.delete(id)
  }
}
