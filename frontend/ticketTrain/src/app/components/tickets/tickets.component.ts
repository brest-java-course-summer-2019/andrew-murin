import {Component, Inject, Input, OnInit} from '@angular/core';
import {TicketService} from "../../services/ticketService/ticket.service";
import {Ticket} from "../../model/Ticket";
import {ActivatedRoute, Router} from "@angular/router";
import {tick} from "@angular/core/testing";

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  tickets: Ticket[];
  displayPayModal = 'none';
  ticket: Ticket;

  constructor(private ticketService: TicketService,
              private route: ActivatedRoute,
              private router: Router) {

    const params = {
      'startDate': this.route.snapshot.queryParamMap.get('startDate'),
      'finishDate': this.route.snapshot.queryParamMap.get('finishDate'),
      'cityFrom': this.route.snapshot.queryParamMap.get('cityFrom'),
      'cityTo': this.route.snapshot.queryParamMap.get('cityTo')
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


  openDialog(index: number): void {
    console.log(index)
    this.displayPayModal = 'block';
  }

  findTicketAndSendToChild(ticketId: string): void {
    this.ticket = this.tickets.find(value => value.id === ticketId);
    this.ticketService.init(this.ticket);
  }

  receiveEventToCloseDialog($event): void {
    this.displayPayModal = $event;
  }
}
