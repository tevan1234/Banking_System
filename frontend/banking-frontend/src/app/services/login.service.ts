import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from 'app/models/api-response.model';
import { loginRequest, loginResponse} from '../models/user.model';

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    private apiUrl = `${environment.apiUrl}`;

    constructor(private http: HttpClient) { }

    login(request : { user : loginRequest }): Observable<ApiResponse<loginResponse>> {
        return this.http.post<ApiResponse<loginResponse>>(`${this.apiUrl}/auth/login`, request);
    }

}