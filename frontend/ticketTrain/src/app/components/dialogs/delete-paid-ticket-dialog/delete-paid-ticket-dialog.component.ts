import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TicketService} from "../../../services/ticketService/ticket.service";

@Component({
  selector: 'app-delete-paid-ticket-dialog',
  templateUrl: './delete-paid-ticket-dialog.component.html',
  styleUrls: ['./delete-paid-ticket-dialog.component.css']
})
export class DeletePaidTicketDialogComponent implements OnInit {

  @Input()
  display = 'none';

  @Input()
  deletePaidTicket: string;

  @Output()
  displayDeletePaidTicketModal = new EventEmitter<string>();



  constructor(private ticketService: TicketService) { }


  ngOnInit(): void {
  }

  closeDialog() {
    this.display = 'none';
    this.displayDeletePaidTicketModal.emit(this.display)
  }

  deletePaidTicketById() {
    this.ticketService.delete(this.deletePaidTicket);
  }
}
