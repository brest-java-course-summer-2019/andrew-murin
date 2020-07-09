import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaidTicketComponent } from './paid-ticket.component';

describe('PaidTicketComponent', () => {
  let component: PaidTicketComponent;
  let fixture: ComponentFixture<PaidTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaidTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaidTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
