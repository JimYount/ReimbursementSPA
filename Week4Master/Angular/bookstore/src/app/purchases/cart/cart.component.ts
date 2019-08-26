import { Component, OnInit } from '@angular/core';
import { Purchase } from '../shared/purchase';
import { PurchaseService } from '../shared/purchase.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  public purchases: Purchase[];
  constructor(
    private purchaseService: PurchaseService
  ) { }

  ngOnInit() {
    this.purchaseService.getPurchases().subscribe(
      purchaseList => {
        purchaseList.sort((purch1, purch2) => {
          const one = purch1.status === 'OPEN' ? 1 : 0;
          const two = purch2.status === 'OPEN' ? 1 : 0;
          return two - one;
        });
        this.purchases = purchaseList;
      }
    );
  }

}
