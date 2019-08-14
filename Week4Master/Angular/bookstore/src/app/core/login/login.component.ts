import { Component, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/shared/user/currentuser';
import { UserService } from 'src/app/shared/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loggedUser: CurrentUser;
  public username: string;
  public password: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.login(null, null).subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
  }

  login() {
    this.userService.login(this.username, this.password).subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
  }

  logout() {
    this.userService.logout().subscribe(
      resp => {
        this.loggedUser = null;
      }
    );
  }

}
