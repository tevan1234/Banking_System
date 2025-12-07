import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';
import { Body } from './components/body/body';
import { Footer } from "./components/footer/footer";
import { ReactiveFormsModule } from '@angular/forms';

import { AgGridModule } from 'ag-grid-angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,               // ✅ Standalone 元件
  imports: [RouterOutlet, Header, Body, Footer, ReactiveFormsModule, AgGridModule], // ✅ 匯入 Header
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('銀行系統');

  constructor(private router: Router) {}

  showHeaderAndFooter() :boolean {
    const currentUrl = this.router.url;
    return currentUrl !== '/login';
  }
}
