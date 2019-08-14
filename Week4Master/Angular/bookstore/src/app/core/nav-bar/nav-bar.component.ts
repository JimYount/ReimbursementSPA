import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/user/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  title = 'Richard\'s BookStore';

  constructor( public route: Router, private userService: UserService) { }

  ngOnInit() {
  }

  isEmployee(): boolean {
    return this.userService.isEmployee();
  }
  isCustomer(): boolean {
    return this.userService.isCustomer();
  }

}
