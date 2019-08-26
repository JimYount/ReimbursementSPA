import { Pipe, PipeTransform } from '@angular/core';
import { Book } from './books/shared/book';

@Pipe({
  name: 'book'
})
export class BookPipe implements PipeTransform {

  transform(books: Book[], searchText: string): Book[] {
    if (!books) {
      return [];
    }
    if (!searchText) {
      searchText = '';
    }
    searchText = searchText.toLowerCase();
    return books.filter(book => {
      const searchNumber: number = +searchText;
      let search = book.title.toLowerCase().includes(searchText)
        || book.price === searchNumber
        || book.stock === searchNumber
        || book.isbn10.includes(searchText);
      if (book.isbn13) {
        search = search || book.isbn13.includes(searchText);
      }
      for (const a of book.authors) {
        console.log(a);
        search = search || a.first.toLowerCase().includes(searchText)
          || a.last.toLowerCase().includes(searchText);
        if (a.about) {
          search = search || a.about.toLowerCase().includes(searchText);
        }
      }
      for (const g of book.genres) {
        search = search || g.genre.toLowerCase().includes(searchText);
      }
      return search;
    });
  }

}
