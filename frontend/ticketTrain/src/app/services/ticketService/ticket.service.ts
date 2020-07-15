import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../../model/Ticket";

@Injectable({
  providedIn: 'root'
})
export class TicketService {


  ticket: Ticket;


  constructor(private http: HttpClient) {
  }


  init(ticket: Ticket): void {
    this.ticket = ticket;
  }

  findAll(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>('http://localhost:8088/api/tickets');
  }

  findAllPaidTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>('http://localhost:8088/api/paid-tickets');
  }

  findPaidTicketsByDate(startDate: string, finishDate: string): Observable<Ticket[]> {
    return this.http.get<Ticket[]>('http://localhost:8088/api/search-paid-tickets', {
      params: {
        'startDate': startDate,
        'finishDate': finishDate
      }
    });
  }

  searchTicket(params): Observable<Ticket[]> {
    console.log('search... ' + params)
    return this.http.get<Ticket[]>('http://localhost:8088/api/search-tickets', {params});
  }

  //TODO: doesn't work
  payTicket(): Observable<any> {

    console.log(this.ticket);

    return this.http.put<any>('http://localhost:8088/api/tickets', {
        "email": "PISKA@mil.ru",
        "fromCity": "BREST",
        "id": "5ecab40aa3e4914f0d7d9853",
        "paymentDate": "2020-07-10T00:00:00Z",
        "ticketCost": 11.2,
        "ticketDate": "2019-09-25T00:00:00Z",
        "toCity": "MINSK"
      },
      {headers: {
          "Content-type": "application/json"}
      }
    );
  }

  addTicket(ticket: Ticket): Observable<void> {
    this.ticket = ticket;
    console.log("work add ticket: " + this.ticket);

    return this.http.post<void>('http://localhost:8088/api/tickets', ticket);
  }

}
