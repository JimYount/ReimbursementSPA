import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../shared/author.service';
import { UserService } from 'src/app/shared/user/user.service';
import { Author } from '../shared/author';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {
  public authors: Author[];
  public author: Author;
  constructor(
    private authorService: AuthorService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.authorService.getAuthors().subscribe(
      authors => this.authors = authors
    );
    this.author = new Author();
  }
  isEmployee(): boolean {
    return this.userService.isEmployee();
  }
  newAuthor(submitted: boolean) {
    if (submitted) {
      this.authors.push(this.author);
      this.author = new Author();
    }
  }
}
