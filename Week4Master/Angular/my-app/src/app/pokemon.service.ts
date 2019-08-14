import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Pokemon } from './pokemon';


// @Injectable is a decorator that marks a class as a TARGET for
// dependency injection. This class needs to have the HttpClient service
// injected into it, and then we'll inject this class into our pokemon component.

// @Injectable is not necessary to make a service able to be injected into a component.
@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  // A Service is an object used by another object as a dependency

  constructor(private http: HttpClient) { }

  // Our service is going to modularize the sending of an http request
  // to the pokeapi and receive a pokemon
  getPokemon(id: number): Observable<Pokemon> {
    return this.http.get('https://pokeapi.co/api/v2/pokemon/' + id).pipe(
      map( resp => resp as Pokemon)
    );
  }
}
