import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from '../../shared/url.service';
import { Observable, pipe } from 'rxjs';
import { map } from 'rxjs/operators';
import { Purchase } from './purchase';
import { Book } from 'src/app/books/shared/book';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {
  private appUrl = this.urlService.getUrl() + '/purchases';
  private headers = new HttpHeaders({'Content-Type': 'application/json' });
  constructor(
    private http: HttpClient,
    private urlService: UrlService
  ) {
    console.log('Purchase Service created');
    console.log(this);
  }

  getPurchases(): Observable<Purchase[]> {
    return this.http.get(this.appUrl, { withCredentials: true }).pipe(
    map(
        resp => resp as Purchase[]
    ));
  }

  getPurchase(id: number): Observable<Purchase> {
    const url = this.appUrl + '/' + id;
    return this.http.get(url, { withCredentials: true }).pipe(
      map(
      resp => resp as Purchase
    ));
  }

  updatePurchase(purch: Purchase): Observable<Purchase> {
    const url = this.appUrl + '/' + purch.id;
    const body = JSON.stringify(purch);
    return this.http.put(url, body, { headers: this.headers, withCredentials: true }).pipe(
      map(
      resp => resp as Purchase
    ));
  }

  /*
   Sending a post request to /purchases results in retrieving a purchase which is labeled as "OPEN"
  */
  createPurchase(): Observable<Purchase> {
    const body = '{}';
    return this.http.post(this.appUrl, body, { headers: this.headers, withCredentials: true }).pipe(
      map(
      resp => resp as Purchase
    ));
  }

  addBook(purchase: Purchase, book: Book): Observable<Purchase> {
    const body = {};
    const url = this.appUrl + '/' + purchase.id + '/book/' + book.id;
    console.log(url);
    return this.http.put(url, body, { headers: this.headers, withCredentials: true }).pipe(
      map(
      resp => resp as Purchase
    ));
  }
  subBook(purchase: Purchase, book: Book): Observable<Purchase> {
    const url = this.appUrl + '/' + purchase.id + '/book/' + book.id;
    console.log(url);
    return this.http.delete(url, { headers: this.headers, withCredentials: true }).pipe(
      map(
      resp => resp as Purchase
    ));
  }
  emptyCart(purchase: Purchase): Observable<object> {
    const url = this.appUrl + '/' + purchase.id;
    console.log(url);
    return this.http.delete(url, { headers: this.headers, withCredentials: true}).pipe(
      map(
      resp => resp
    ));
  }
}
