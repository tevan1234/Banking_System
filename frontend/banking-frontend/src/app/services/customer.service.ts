import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from 'app/models/api-response.model';
import { Customer, CustomerApplyRequest } from 'app/models/customer.model';

@Injectable({
    providedIn: "root"
})
export class CustomerService {
    private apiUrl = `${environment.apiUrl}/Customer`;

    constructor(private http: HttpClient) { }

    getCustomers(): Observable<any> {
        return this.http.get(`${this.apiUrl}/listAll`);
    }

    getCustomerByUserId(userId: number): Observable<ApiResponse<Customer>> {
        return this.http.get<ApiResponse<Customer>>(`${this.apiUrl}/userId`, {params: {userId}});
    }

    applyCustomer(customerApplyRequest : CustomerApplyRequest) : Observable<ApiResponse<Customer>> {
        return this.http.post<ApiResponse<Customer>>(`${this.apiUrl}/applyCustomer`, customerApplyRequest)
    }
}