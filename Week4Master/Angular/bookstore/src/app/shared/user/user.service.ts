import { Injectable } from '@angular/core';
import { UrlService } from '../url.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from './employee';
import { Customer } from './customer';
import { CurrentUser } from './currentuser';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private appUrl = this.url.getUrl() + '/login';
  private headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
  private employee: Employee;
  private customer: Customer;

  constructor(private url: UrlService, private http: HttpClient) { }

  login(username: string, password: string): Observable<CurrentUser> {
    if ( username && password ) {
      // we are attempting to log in
      const body = `user=${username}&pass=${password}`;
      // We have to tell our app that we're sending form data, so add the headers
      return this.http.post(this.appUrl, body, {headers: this.headers, withCredentials: true}).pipe(
        map( resp => {
          const user: CurrentUser = resp as CurrentUser;
          if (user) {
            this.employee = user.employee;
            this.customer = user.customer;
          }
          return user;
        })
      );
    } else {
      // checking to see if we're logged in
      return this.http.get(this.appUrl, {withCredentials: true}).pipe(
        map( resp => {
          const user: CurrentUser = resp as CurrentUser;
          if (user) {
            this.employee = user.employee;
            this.customer = user.customer;
          }
          return user;
        })
      );
    }
  }
  logout(): Observable<object> {
    return this.http.delete(this.appUrl, {withCredentials: true}).pipe(
      map(success => {
        this.employee = null;
        this.customer = null;
        return success;
      })
    );
  }
  getCustomer(): Customer {
    return this.customer;
  }
  getEmployee(): Employee {
    return this.employee;
  }
  isEmployee(): boolean {
    return (this.employee !== undefined && this.employee !== null);
  }
  isCustomer(): boolean {
    return (this.customer !== undefined && this.customer !== null);
  }
}
