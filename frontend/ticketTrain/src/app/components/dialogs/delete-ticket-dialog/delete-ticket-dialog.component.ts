import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TicketService} from "../../../services/ticketService/ticket.service";
import {log} from "util";

@Component({
  selector: 'app-delete-ticket-dialog',
  templateUrl: './delete-ticket-dialog.component.html',
  styleUrls: ['./delete-ticket-dialog.component.css']
})
export class DeleteTicketDialogComponent implements OnInit {

  @Input()
  display = 'none';

  @Input()
  deleteTicket: string;

  @Output()
  displayDeleteModal = new EventEmitter<string>();


  constructor(private ticketService: TicketService) { }


  ngOnInit(): void {
  }


  closeDialog(): void {
    this.display = 'none';
    this.displayDeleteModal.emit(this.display);
  }


  deleteTicketById() {
    this.ticketService.delete(this.deleteTicket);
  }
}
