import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../../model/Ticket";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private URL: string = 'http://localhost:8088/api/';

  private TICKETS: string = 'tickets';
  // private URL: string = 'http://localhost:8088/';



  constructor(private http: HttpClient) {

  }

  findAll(): Observable<Ticket[]>{
    return this.http.get<Ticket[]>('http://localhost:8088/api/tickets');

  }
}
