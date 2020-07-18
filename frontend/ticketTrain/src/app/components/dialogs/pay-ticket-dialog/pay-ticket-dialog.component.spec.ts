import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayTicketDialogComponent } from './pay-ticket-dialog.component';

describe('PayDialogComponent', () => {
  let component: PayTicketDialogComponent;
  let fixture: ComponentFixture<PayTicketDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayTicketDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayTicketDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
