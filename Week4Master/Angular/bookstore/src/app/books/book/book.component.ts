import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../shared/book';
import { UserService } from 'src/app/shared/user/user.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Router, ActivatedRoute } from '@angular/router';
import { BookService } from '../shared/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  @Input() book: Book;
  constructor(
    private bookService: BookService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('bob');
    if (id) {
      this.bookService.getBook(id).subscribe(
        book => {
          this.book = book;
        }
      )
    }
  }

  isEmployee(): boolean {
    return this.userService.isEmployee();
  }
  isCustomer(): boolean {
    return this.userService.isCustomer();
  }
  editBook() {
    this.router.navigate(['/books/edit', this.book.id]);
  }

}
