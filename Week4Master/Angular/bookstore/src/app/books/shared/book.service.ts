import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Book } from './book';
import { UrlService } from 'src/app/shared/url.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private appUrl = this.url.getUrl() + '/books';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private url: UrlService, private http: HttpClient) { }

  getBooks(): Observable<Book[]> {
    return this.http.get(this.appUrl, {withCredentials: true} ).pipe(
      map( resp => resp as Book[])
    );
  }
  getBook(id: number): Observable<Book> {
    const url: string = this.appUrl + '/' + id;
    return this.http.get(url, {withCredentials: true}).pipe(
      map(resp => resp as Book)
    );
  }
  updateBook(book: Book): Observable<Book>{
    const body = JSON.stringify(book);
    if(book.id) {
      const url = this.appUrl + '/' + book.id;
      return this.http.put(url, body,
        {headers: this.headers, withCredentials: true}).pipe(
          map(resp => resp as Book)
        );
    } else {
      return this.http.post(this.appUrl, body,
        { headers: this.headers, withCredentials: true}).pipe(
          map(resp => resp as Book)
      );
    }
  }
}
