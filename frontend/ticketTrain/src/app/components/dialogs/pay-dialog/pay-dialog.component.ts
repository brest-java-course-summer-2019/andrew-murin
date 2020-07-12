import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TicketService} from "../../../services/ticketService/ticket.service";
import {Ticket} from "../../../model/Ticket";
import {tick} from "@angular/core/testing";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-pay-dialog',
  templateUrl: './pay-dialog.component.html',
  styleUrls: ['./pay-dialog.component.css']
})
export class PayDialogComponent implements OnInit {

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
    this.ticket.paymentDate = new Date();

    this.ticketService.payTicket();
  }

  closeDialog(): void {
    this.display = 'none';
    this.displayPayModal.emit(this.display);
  }


}
