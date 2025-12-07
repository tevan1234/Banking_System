import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CreateUserRequest, User } from '../models/user.model';
import { ApiResponse } from 'app/models/api-response.model';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private apiUrl = `${environment.apiUrl}/User`;

    constructor(private http: HttpClient) { }

    // GET 請求
    getUsers(): Observable<any> {
        return this.http.get(`${this.apiUrl}/listAll`);
    }

    // POST 請求
    addUser(request: { user: CreateUserRequest }): Observable<ApiResponse<User>> {
        return this.http.post<ApiResponse<User>>(`${this.apiUrl}/addUser`, request);
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
