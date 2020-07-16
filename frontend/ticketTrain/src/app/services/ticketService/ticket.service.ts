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
    return this.http.get<Ticket[]>('http://localhost:8088/api/search-tickets', {params});
  }

  //TODO: doesn't work
  payTicket(ticket: Ticket) {

    console.log(ticket);

    return this.http.put('http://localhost:8088/api/tickets', ticket, httpOptions).subscribe();
  }

  addTicket(ticket: Ticket) {

    return this.http.post('http://localhost:8088/api/tickets', ticket, httpOptions).pipe(
      tap(() => this.log("succcsess send request")),
      catchError(this.handleError<any>('add Ticket'))
    ).subscribe();
  }

  delete(id: string) {

    this.log("Id for delete: " + id);
    return this.http.delete('http://localhost:8088/api/tickets/' + id, httpOptions).subscribe();
  }
}
