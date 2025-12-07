import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 

@Injectable({
  providedIn: 'root'
})
export class Apiservice{
    private apiUrl = environment.apiUrl;

    constructor(private http: HttpClient){}

    // GET 請求
    getData(): Observable<any> {
    return this.http.get(`${this.apiUrl}/data`);
  }

  // POST 請求
  postData(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/data`, data);
  }

  // PUT 請求
  updateData(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/data/${id}`, data);
  }

  // DELETE 請求
  deleteData(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/data/${id}`);
  }
}
