import { Sprites } from './sprites';
import { Stat } from './stat';
import { Type } from './type';

export class Pokemon {
  id: number;
  name: string;
  height: number;
  weight: number;
  types: Type[];
  stats: Stat[];
  sprites: Sprites;
}
