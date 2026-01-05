import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from 'app/services/auth.service';
import { DrawerService } from 'app/services/drawer.service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule,AsyncPipe],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class Header {
  title = "我的網站";

  constructor(private router: Router,protected authService: AuthService, private drawerService: DrawerService) { }

  openDrawer() {
    this.drawerService.open();
    console.log('Drawer opened!');
  }
}
