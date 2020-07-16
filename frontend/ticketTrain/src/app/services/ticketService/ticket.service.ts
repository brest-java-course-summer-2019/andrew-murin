import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Ticket} from "../../model/Ticket";
import {catchError, map, tap} from "rxjs/operators"


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}


@Injectable({
  providedIn: 'root'
})
export class TicketService {


  TICKETS_API: string = 'http://localhost:8088/api/tickets/';
  SEARCH_TICKETS_API: string = 'http://localhost:8088/api/search-tickets';
  PAID_TICKETS_API: string = 'http://localhost:8088/api/paid-tickets';
  SEARCH_PAID_TICKETS_API: string = 'http://localhost:8088/api/search-paid-tickets';


  constructor(private http: HttpClient) {
  }


  private log(message: string): void {
    console.log("ticketService: " + message);
  }


  private handleError<T>(operation = 'operation', result? :T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }



  findAll(): Observable<Ticket[]> {

    return this.http.get<Ticket[]>(this.TICKETS_API);
  }


  findAllPaidTickets(): Observable<Ticket[]> {

    return this.http.get<Ticket[]>(this.PAID_TICKETS_API);
  }


  findPaidTicketsByDate(startDate: string, finishDate: string): Observable<Ticket[]> {

    return this.http.get<Ticket[]>(this.SEARCH_PAID_TICKETS_API, {
      params: {
        'startDate': startDate,
        'finishDate': finishDate
      }
    });
  }


  searchTicket(params): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(this.SEARCH_TICKETS_API, {params});
  }


  payTicket(ticket: Ticket) {

    return this.http.put(this.TICKETS_API, ticket, httpOptions).subscribe();
  }


  addTicket(ticket: Ticket) {

    return this.http.post(this.TICKETS_API, ticket, httpOptions).pipe(
      tap(() => this.log("success send request")),
      catchError(this.handleError<any>('add Ticket'))
    ).subscribe();
  }


  delete(id: string) {

    this.log("Id for delete: " + id);
    return this.http.delete(this.TICKETS_API + id, httpOptions).subscribe();
  }
}
