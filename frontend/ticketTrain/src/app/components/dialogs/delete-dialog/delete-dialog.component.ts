import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TicketService} from "../../../services/ticketService/ticket.service";
import {log} from "util";

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent implements OnInit {

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
