import { Author } from './author';
import { Genre } from './genre';

export class Book {
  id: number;
  title: string;
  isbn10: string;
  isbn13: string;
  authors: Author[];
  genres: Genre[];
  price: number;
  stock: number;
  cover: string;
}
