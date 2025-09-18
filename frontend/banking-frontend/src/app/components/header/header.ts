import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class Header {
  title = "我的網站";
  constructor() {}
  greet() {
    console.log("Hello from Header");
  }
}
