import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CreateUserRequest, User, UserDetail, UserProfile } from '../models/user.model';
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

    getUserDetail(userId: number): Observable<ApiResponse<UserDetail>> {
        return this.http.get<ApiResponse<UserDetail>>(`${this.apiUrl}/detail`, {params: {userId}});
    }

    getByUserId(userId: number): Observable<ApiResponse<User>> {
        return this.http.get<ApiResponse<User>>(`${this.apiUrl}/getByUserId`, {params: {userId}});
    }

    getByUserName(userName: string) : Observable<ApiResponse<User>> {
        return this.http.get<ApiResponse<User>>(`${this.apiUrl}/getByUserName`, {params: {userName}});
    }

    // POST 請求
    addUser(request: { user: CreateUserRequest }): Observable<ApiResponse<User>> {
        return this.http.post<ApiResponse<User>>(`${this.apiUrl}/addUser`, request);
    }

    // PUT 請求
    updateData(id: number, data: any): Observable<any> {
        return this.http.put(`${this.apiUrl}/data/${id}`, data);
    }

    updateProfile(profile: Partial<UserProfile> ): Observable<ApiResponse<UserProfile>> {
        return this.http.put<ApiResponse<UserProfile>>(`${this.apiUrl}/updateProfile`, profile);
    }

    // DELETE 請求
    deleteData(id: number): Observable<any> {
        return this.http.delete(`${this.apiUrl}/data/${id}`);
    }
}
