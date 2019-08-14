import { Address } from './address';
import { Book } from 'src/app/books/shared/book';
import { User } from './user';

export class Customer extends User {
  address: Address;
  readingList: Book[];
  favoriteColor: string;
}
