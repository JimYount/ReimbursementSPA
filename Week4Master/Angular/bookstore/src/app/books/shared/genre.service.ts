import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Genre } from './genre';
import { UrlService } from 'src/app/shared/url.service';

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  private appUrl = this.urlService.getUrl() + '/genres';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private http: HttpClient,
    private urlService: UrlService
  ) { }

  getgenres(): Observable<Genre[]> {
    return this.http.get(this.appUrl, { withCredentials: true })
      .pipe(map(
        resp => resp as Genre[]
      ));
  }
  getGenre(id: number): Observable<Genre> {
    return this.http.get(this.appUrl + '/' + id, { withCredentials: true })
      .pipe(map(
        resp => resp as Genre
      ));
  }
  updateGenre(genre: Genre): Observable<Genre> {
    const body = JSON.stringify(genre);
    if (!genre.id) {
      // If there is not id on the genre, it is not from the database.
      // That means we are trying to make a new one!
      return this.http.post(this.appUrl, body,
        { headers: this.headers, withCredentials: true }).pipe(
        map( resp => resp as Genre )
      );
    } else {
      // If there is an id, we are...
      // updating an existing resource
      const url = this.appUrl + '/' + genre.id;
      return this.http.put(url, body, { headers: this.headers, withCredentials: true })
      .pipe(map(
        resp => resp as Genre
      ));
    }
  }
}
