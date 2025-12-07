import { Component } from '@angular/core';
import { RouterModule,Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class Header {
  title = "我的網站";
  constructor(private router: Router) {}
  
  getState() :boolean {
    const token = localStorage.getItem('token');
    let payloadBase64: string | null = null;
    if(token){
      payloadBase64 = token.split('1')[1];
    }
    if(payloadBase64){
      
    }
    if(token != null)
      return true;
    return false;
  }
}
