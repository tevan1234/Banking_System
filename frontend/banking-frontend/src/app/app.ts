import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';   // 假設檔案是 src/app/header/header.ts

@Component({
  selector: 'app-root',
  standalone: true,               // ✅ Standalone 元件
  imports: [RouterOutlet, Header], // ✅ 匯入 Header
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('銀行系統');
}
