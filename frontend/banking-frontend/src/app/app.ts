import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';
import { Body } from './components/body/body';
import { Footer } from "./components/footer/footer";
import { Drawer } from './components/drawer/drawer';
import { ReactiveFormsModule } from '@angular/forms';

import { AgGridModule } from 'ag-grid-angular';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { UserService } from './services/user.service';
import { filter, switchMap, take } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,               // ✅ Standalone 元件
  imports: [RouterOutlet, Header, Body, Footer, Drawer, ReactiveFormsModule, AgGridModule], // ✅ 匯入 Header
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App implements OnInit {
  protected readonly title = signal('銀行系統');

  constructor(private router: Router, private authSerVice: AuthService, private userService: UserService) { }

  ngOnInit() {
    this.authSerVice.isLoggedIn$.pipe(filter(isLoggedIn => isLoggedIn), take(1)
      , switchMap(() => this.authSerVice.userId$), filter(id => !!id), take(1)
      , switchMap(id => this.userService.getByUserId(id!))
    ).subscribe(res => {
      if (res.success) {
        this.authSerVice.setUserName(res.data.userName);
      }
    })
  }

  showHeaderAndFooter(): boolean {
    const currentUrl = this.router.url;
    return (currentUrl !== '/login' && currentUrl !== '/add-user');
  }
}
