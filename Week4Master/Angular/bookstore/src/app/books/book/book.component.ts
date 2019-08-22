import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../shared/book';
import { UserService } from 'src/app/shared/user/user.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Router, ActivatedRoute } from '@angular/router';
import { BookService } from '../shared/book.service';
import { PurchaseService } from 'src/app/purchases/shared/purchase.service';

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
    private purchaseService: PurchaseService,
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
      );
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
  addToCart() {
    this.purchaseService.createPurchase().subscribe(
      purch => {
        // once the purchase is returned.
        // we need to add a book to it.
        this.purchaseService.addBook(purch, this.book)
          .subscribe(updatedPurchase => {
            // once this is done. we navigate to our purchase
            this.router.navigate(['/purch', updatedPurchase.id]);
          });
      }
    );
  }
}
