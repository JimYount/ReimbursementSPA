import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { EditBookComponent } from './books/edit-book/edit-book.component';
import { BookComponent } from './books/book/book.component';
import { BookShelfComponent } from './books/book-shelf/book-shelf.component';
import { PurchaseComponent } from './purchases/purchase/purchase.component';
import { CartComponent } from './purchases/cart/cart.component';
import { GenreComponent } from './books/genre/genre.component';
import { AuthorListComponent } from './books/author-list/author-list.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'books/edit',
    component: EditBookComponent
  },
  {
    path: 'books/edit/:id',
    component: EditBookComponent
  },
  {
    path: 'books/:bob',
    component: BookComponent
  },
  {
    path: 'books',
    component: BookShelfComponent
  },
  {
    path: 'purch/:id',
    component: PurchaseComponent
  },
  {
    path: 'purch',
    component: CartComponent
  },
  {
    path: 'genres',
    component: GenreComponent
  },
  {
    path: 'authors',
    component: AuthorListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
