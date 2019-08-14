import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../shared/book';
import { Author } from '../shared/author';
import { Genre } from '../shared/genre';
import { GenreService } from '../shared/genre.service';
import { AuthorService } from '../shared/author.service';
import { BookService } from '../shared/book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {
  public book: Book;
  public authorList: Author[];
  public genreList: Genre[];

  constructor(
    private bookService: BookService,
    private authorService: AuthorService,
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    console.log('Hello from edit-book\'s oninit()');
    const id = +this.route.snapshot.paramMap.get('id');
    console.log(id);
    if (id) {
      this.bookService.getBook(id).subscribe(
        book => {
          // set current book to the book retrieved.
          this.book = book;
          // retrieve all authors and splice the book's authors out of that list.
          this.authorService.getAuthors().subscribe(
            authors => {
              this.authorList = authors;
              if (this.book.authors) {
                this.book.authors.forEach(
                  author => this.authorList.splice(this.authorList.indexOf(author, 1))
                );
              }
            }
          );
          // Do the same thing to genres
          this.genreService.getgenres().subscribe(
            genres => {
              this.genreList = genres;
              if (this.book.genres) {
                this.book.genres.forEach(
                  genre => this.genreList.splice(this.genreList.indexOf(genre, 1))
                );
              }
            }
          );
        }
      );
    } else {
      this.book = new Book();
      this.book.authors = [];
      this.book.genres = [];
      this.genreService.getgenres().subscribe(
        genres => {
          this.genreList = genres;
        }
      );
      this.authorService.getAuthors().subscribe(
        authors => {
          this.authorList = authors;
        }
      );
    }
  }

  removeAuthor(a: Author): void {
    // remove the author from the book
    this.book.authors.splice(this.book.authors.indexOf(a), 1);
    // add the author to the list
    this.authorList.push(a);
  }

  addAuthor(a: Author): void {
    // add into book
    this.book.authors.push(a);
    // remove from list
    this.authorList.splice(this.authorList.indexOf(a), 1);
  }

  removeGenre(a: Genre): void {
    // remove the genre from the book
    this.book.genres.splice(this.book.genres.indexOf(a), 1);
    // add the genre to the list
    this.genreList.push(a);
  }

  addGenre(a: Genre): void {
    // add into book
    this.book.genres.push(a);
    // remove from list
    this.genreList.splice(this.genreList.indexOf(a), 1);
  }

  submit(): void {
    this.bookService.updateBook(this.book).subscribe(
      book => {
        this.book = book;
        this.router.navigate(['/books']);
      }
    );
  }
}
