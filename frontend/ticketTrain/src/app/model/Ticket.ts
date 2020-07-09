import {City} from "./City";

export class Ticket {

  id: string;

  ticketCost: string;

  ticketDate: Date;

  fromCity: City;

  toCity: City;

  paymentDate?: Date;

  email?: string;


  constructor(
    id: string,
    ticketCost: string,
    ticketDate: Date,
    fromCity: City,
    toCity: City,
    paymentDate?: Date,
    email?: string
  )
  {
    this.id = id;
    this.ticketCost = ticketCost;
    this.ticketDate = ticketDate;
    this.fromCity = fromCity;
    this.toCity = toCity;
    this.paymentDate = paymentDate;
    this.email = email;
  }
}
