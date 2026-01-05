import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CreateUserRoleRequest, userRole } from 'app/models/userRole.model';
import { ApiResponse } from 'app/models/api-response.model';

@Injectable({
    providedIn: 'root'
})
export class userRoleService {
    private apiUrl = `${environment.apiUrl}/UserRole`;

    constructor(private http: HttpClient) {}

    addUserRole(request : { userRole : CreateUserRoleRequest}) : Observable<ApiResponse<userRole>>{
        return this.http.post<ApiResponse<userRole>>('${this.apiUrl}/addUserRole',request);
    }
}