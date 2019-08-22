import { Component, OnInit, Input } from '@angular/core';
import { PurchaseService } from '../shared/purchase.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Purchase } from '../shared/purchase';
import { Customer } from '../../shared/user/customer';
import { Book } from '../../books/shared/book';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  @Input() purchase: Purchase;

  constructor(
    private purchaseService: PurchaseService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    if (!this.purchase) {
      this.purchase = new Purchase();
      this.purchase.cust = new Customer();
    }
    const id = +this.route.snapshot.paramMap.get('id');
    if (id) {
      console.log(+id);
      this.purchaseService.getPurchase(id).subscribe(purch => this.purchase=purch);
    }
  }
  add(book: Book): void {
    this.purchaseService.addBook(this.purchase, book)
    .subscribe(purch => this.purchase = purch);
  }
  sub(book: Book): void {
    this.purchaseService.subBook(this.purchase, book)
    .subscribe(purch => this.purchase = purch);
  }

  makePurchase(): void {
    this.purchase.status = 'CLOSED';
    this.purchaseService.updatePurchase(this.purchase)
    .subscribe(p => this.purchase = p);
  }
  isOpen(): boolean {
    return this.purchase.status === 'OPEN';
  }
  emptyCart(): void {
    this.purchaseService.emptyCart(this.purchase)
    .subscribe(p => this.router.navigate(['/books']));
  }
}
