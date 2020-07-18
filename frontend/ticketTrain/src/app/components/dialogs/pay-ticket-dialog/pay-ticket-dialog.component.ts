import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TicketService} from "../../../services/ticketService/ticket.service";
import {Ticket} from "../../../model/Ticket";

@Component({
  selector: 'app-pay-ticket-dialog',
  templateUrl: './pay-ticket-dialog.component.html',
  styleUrls: ['./pay-ticket-dialog.component.css']
})
export class PayTicketDialogComponent implements OnInit {

  @Input()
  display = 'none';

  @Input()
  ticket: Ticket;

  @Output()
  displayPayModal = new EventEmitter<string>();


  constructor(private ticketService: TicketService) { }


  ngOnInit(): void {
  }


  payTicket(email: string): void {
    this.ticket.email = email;
    this.ticket.paymentDate = new Date().toISOString();

    this.ticketService.payTicket(this.ticket);
  }


  closeDialog(): void {
    this.display = 'none';
    this.displayPayModal.emit(this.display);
  }


}
