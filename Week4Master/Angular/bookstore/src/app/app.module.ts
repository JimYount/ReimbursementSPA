import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorComponent } from './books/author/author.component';
import { BookComponent } from './books/book/book.component';
import { BookShelfComponent } from './books/book-shelf/book-shelf.component';
import { EditBookComponent } from './books/edit-book/edit-book.component';
import { GenreComponent } from './books/genre/genre.component';
import { AuthorListComponent } from './books/author-list/author-list.component';
import { BookService } from './books/shared/book.service';
import { AuthorService } from './books/shared/author.service';
import { GenreService } from './books/shared/genre.service';
import { LoginComponent } from './core/login/login.component';
import { NavBarComponent } from './core/nav-bar/nav-bar.component';
import { UrlService } from './shared/url.service';
import { UserService } from './shared/user/user.service';
import { HomeComponent } from './home/home.component';
import { PurchaseComponent } from './purchases/purchase/purchase.component';
import { CartComponent } from './purchases/cart/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthorComponent,
    BookComponent,
    BookShelfComponent,
    EditBookComponent,
    GenreComponent,
    AuthorListComponent,
    LoginComponent,
    NavBarComponent,
    HomeComponent,
    PurchaseComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    BookService,
    AuthorService,
    GenreService,
    UrlService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
