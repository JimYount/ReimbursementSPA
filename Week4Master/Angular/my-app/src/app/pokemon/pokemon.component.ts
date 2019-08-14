import { Component, OnInit, Input } from '@angular/core';
import { Pokemon } from '../pokemon';
import { Type } from '../type';

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.css']
})
export class PokemonComponent implements OnInit {

  @Input() pokemon: Pokemon;

  constructor() { }

  ngOnInit() {
    if (!this.pokemon) {
      this.pokemon = new Pokemon();
      this.pokemon.height = 166;
      this.pokemon.weight = 254;
      this.pokemon.id = 5;
      this.pokemon.name = 'Charmandizard';
      this.pokemon.stats = [];
    }
  }

  getTypeBySlot(slot: number): Type {
    return this.pokemon.types.find((t) => t.slot === slot);
  }

}
