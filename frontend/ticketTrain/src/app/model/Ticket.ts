import {City} from "./City";

export class Ticket {

  id: string;

  ticketCost: number;

  ticketDate: string;

  fromCity: City;

  toCity: City;

  paymentDate?: string;

  email?: string;


  constructor(
    ticketCost: number,
    ticketDate: string,
    fromCity: City,
    toCity: City,
    paymentDate?: string,
    email?: string
  )
  {
    this.ticketCost = ticketCost;
    this.ticketDate = ticketDate;
    this.fromCity = fromCity;
    this.toCity = toCity;
    this.paymentDate = paymentDate;
    this.email = email;
  }
}
