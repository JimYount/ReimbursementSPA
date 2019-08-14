import { Component, OnInit } from '@angular/core';
import { Book } from '../shared/book';
import { BookService } from '../shared/book.service';

@Component({
  selector: 'app-book-shelf',
  templateUrl: './book-shelf.component.html',
  styleUrls: ['./book-shelf.component.css']
})
export class BookShelfComponent implements OnInit {
  books: Book[];
  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getBooks().subscribe( (books) => {
      this.books = books;
    });
  }

}
