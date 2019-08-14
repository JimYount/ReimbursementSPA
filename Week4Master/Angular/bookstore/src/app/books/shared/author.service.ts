import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from 'src/app/shared/url.service';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Author } from './author';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private appUrl = this.urlSource.getUrl() + '/authors';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(
    private http: HttpClient,
    private urlSource: UrlService
  ) { }

  getAuthor(id: number): Observable<Author> {
    return this.http.get(this.appUrl + '/' + id, { withCredentials: true }).pipe(
      map( resp => resp as Author ));
  }
  getAuthors(): Observable<Author[]> {
    return this.http.get(this.appUrl, { withCredentials: true }).pipe(
      map( resp => resp as Author[] ));
  }
  updateAuthor(author: Author): Observable<Author> {
    const body = JSON.stringify(author);
    if ( !author.id ) {
      // If the author doesn't have an id, it isn't in the database
      // We need to add it, so post
      return this.http.post(this.appUrl, body,
          {headers: this.headers, withCredentials: true}).pipe(
            map( resp => resp as Author ));
    } else {
      // It is in the database and we need to update
      return this.http.put(this.appUrl + '/' + author.id, body,
        { headers: this.headers, withCredentials: true}).pipe(
          map( resp => resp as Author));
    }
  }
}
