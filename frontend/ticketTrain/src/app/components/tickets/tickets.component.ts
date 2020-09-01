import {Component, OnInit} from '@angular/core';
import {TicketService} from "../../services/ticketService/ticket.service";
import {Ticket} from "../../model/Ticket";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {filter} from "rxjs/operators";


@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  tickets: Ticket[];
  displayPayModal = 'none';
  displayDeleteModal = 'none';
  displayEditModal = 'none'
  ticket: Ticket;
  deleteTicket: string;

  isSearching: boolean = false;

  constructor(private ticketService: TicketService,
              private route: ActivatedRoute,
              private router: Router) {

    let urlRegex = new RegExp("/tickets[?]cityFrom=[A-Z]+&cityTo=[A-Z]+&startDate=[-0-9]+&finishDate=[-0-9]+");
    this.isSearching = urlRegex.test(this.router.url);

    if (this.isSearching) {

      const params = {
        'startDate': this.route.snapshot.queryParamMap.get('startDate'),
        'finishDate': this.route.snapshot.queryParamMap.get('finishDate'),
        'cityFrom': this.route.snapshot.queryParamMap.get('cityFrom'),
        'cityTo': this.route.snapshot.queryParamMap.get('cityTo'),
      };

      this.ticketService.searchTicket(params).subscribe(tickets => {
        this.tickets = tickets
      });

    }
  }


  ngOnInit(): void {

    this.ticketService.findAll().subscribe(tickets => {
      if (!this.isSearching)
        this.tickets = tickets
    });

  }


  openDialog(): void {
    this.displayPayModal = 'block';
  }

  openDeleteDialog(): void {
    this.displayDeleteModal = 'block';
  }

  openEditDialog(): void {
    this.displayEditModal = 'block'
  }



  findTicketAndSendToChild(ticketId: string): void {
    this.ticket = this.tickets.find(value => value.id === ticketId);
  }


  receiveEventToClosePayDialog($event): void {
    this.displayPayModal = $event;
  }

  receiveEventToCloseDeleteDialog($event): void {
    this.displayDeleteModal = $event;
  }


}
