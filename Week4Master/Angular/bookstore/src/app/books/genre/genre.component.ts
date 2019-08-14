import { Component, OnInit } from '@angular/core';
import { GenreService } from '../shared/genre.service';
import { Genre } from '../shared/genre';
import { UserService } from 'src/app/shared/user/user.service';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css']
})
export class GenreComponent implements OnInit {
  genres: Genre[];
  genre: Genre;
  constructor(
    private genreService: GenreService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.genre = new Genre();
    this.genreService.getgenres().subscribe( resp => {
      this.genres = resp;
      this.genres.sort( (g1, g2) => g1.id - g2.id );
    });
  }

  isEmployee(): boolean {
    return this.userService.isEmployee();
  }

  submit(): void {
    this.genreService.updateGenre(this.genre).subscribe(
      resp => {
        this.genre = new Genre();
        this.genres.push(resp);
      }
    );
  }
}
