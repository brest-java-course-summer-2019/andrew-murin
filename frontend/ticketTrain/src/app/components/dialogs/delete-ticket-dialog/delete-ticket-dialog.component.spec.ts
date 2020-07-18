import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteTicketDialogComponent } from './delete-ticket-dialog.component';

describe('DeleteDialogComponent', () => {
  let component: DeleteTicketDialogComponent;
  let fixture: ComponentFixture<DeleteTicketDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteTicketDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTicketDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
