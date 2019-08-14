import { Component } from '@angular/core';
import { PokemonService } from './pokemon.service';
import { Pokemon } from './pokemon';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-app';
  searchId = '169';
  pokemon: Pokemon;
  image = 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/' +
    'International_Pok%C3%A9mon_logo.svg/375px-International_Pok%C3%A9mon_logo.svg.png';

  constructor(private pokeService: PokemonService) { }

  search() {
    this.pokeService.getPokemon(+this.searchId).subscribe(
      (resp) => {
        this.pokemon = resp;
        console.log(this.pokemon);
      });
  }
}
