import { Component, OnInit } from '@angular/core';
import {TicketService} from "../../services/ticketService/ticket.service";
import {Ticket} from "../../model/Ticket";

@Component({
  selector: 'app-paid-tickets',
  templateUrl: './paid-tickets.component.html',
  styleUrls: ['./paid-tickets.component.css']
})
export class PaidTicketsComponent implements OnInit {

  paidTickets: Ticket[];
  ticketCount: number;
  totalSum: number;
  searchPaidTickets: boolean = false;


  constructor(private ticketService: TicketService) { }


  ngOnInit(): void {
    this.ticketService.findAllPaidTickets().subscribe(paidTickets => {

        this.paidTickets = paidTickets;

        this.ticketCount = paidTickets.length;

        this.totalSum = Number(paidTickets
          .filter(payment => payment.ticketCost !== null)
          .map(payment => payment.ticketCost)
          .reduce((acc,item ) => acc + item, 0)
          .toFixed(2));
      }
    );
  }


  onSearchPaidTicketsByDate(startDate: string, finishDate: string) {

    this.ticketService.findPaidTicketsByDate(startDate, finishDate).subscribe(paidTickets => {

      this.paidTickets = paidTickets;

      this.ticketCount = paidTickets.length;

      this.totalSum = paidTickets
        .filter(payment => payment.ticketCost !== null)
        .map(payment => payment.ticketCost)
        .reduce((acc,item ) => acc + item, 0);
    })

    this.searchPaidTickets = !this.searchPaidTickets;
  }


}
