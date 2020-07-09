import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../../model/Ticket";

@Injectable({
  providedIn: 'root'
})
export class TicketService {


  constructor(private http: HttpClient) {
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
    console.log("send paras to server: " + params);
    return this.http.get<Ticket[]>('http://localhost:8088/api/search-tickets', {params});
  }
}