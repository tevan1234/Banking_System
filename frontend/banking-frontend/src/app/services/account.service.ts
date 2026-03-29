import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CreateUserRequest, User, UserDetail, UserProfile } from '../models/user.model';
import { ApiResponse } from 'app/models/api-response.model';
import { AccountDetail, applyAccount } from 'app/models/account.model';

@Injectable({
    providedIn: "root"
})
export class AccountService {
    private apiUrl = `${environment.apiUrl}/Account`;

    constructor(private http:HttpClient) {}

    getAccounts(): Observable<any> {
        return this.http.get(`${this.apiUrl}/listAll`);
    }

    getDetailByCustomerId(customerId: number): Observable<ApiResponse<AccountDetail>> {
        return this.http.get<ApiResponse<AccountDetail>>(`${this.apiUrl}/getDetailByCustomerId`, {params: {customerId}});
    }
}