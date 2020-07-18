import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletePaidTicketDialogComponent } from './delete-paid-ticket-dialog.component';

describe('DeletePaidTicketDialogComponent', () => {
  let component: DeletePaidTicketDialogComponent;
  let fixture: ComponentFixture<DeletePaidTicketDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletePaidTicketDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletePaidTicketDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
