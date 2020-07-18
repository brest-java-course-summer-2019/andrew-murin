import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-delete-paid-ticket-dialog',
  templateUrl: './delete-paid-ticket-dialog.component.html',
  styleUrls: ['./delete-paid-ticket-dialog.component.css']
})
export class DeletePaidTicketDialogComponent implements OnInit {

  @Input()
  display = 'none';

  @Output()
  displayDeletePaidTicketModal = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  closeDialog() {
    this.display = 'none';
    this.displayDeletePaidTicketModal.emit(this.display)
  }

  deletePaidTicketById() {

  }
}
