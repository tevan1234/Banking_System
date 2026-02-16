import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RouterModule, Router } from '@angular/router';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from 'app/models/api-response.model';
import { loginRequest, loginResponse } from '../models/user.model';
import { jwtDecode, JwtPayload } from 'jwt-decode';

interface UserPayload extends JwtPayload {
    userName?: string;
    roles?: string[];
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = `${environment.apiUrl}`;
    private isLoggedInSubject = new BehaviorSubject<boolean>(this.checkTokenValidity());
    isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();
    private userNameSubject = new BehaviorSubject<string | null>(this.getInitialUsername());
    userName$: Observable<string | null> = this.userNameSubject.asObservable();
    private roleSubject = new BehaviorSubject<string[]>(this.getRoles());
    role$: Observable<string[]> = this.roleSubject.asObservable();

    constructor(private http: HttpClient, private router: Router) { }

    login(request: { user: loginRequest }): Observable<ApiResponse<loginResponse>> {
        return this.http.post<ApiResponse<loginResponse>>(`${this.apiUrl}/auth/login`, request);
    }

    private getToken(): string | null {
        return localStorage.getItem('token');
    }

    private isTokenExpired(token: string): boolean {
        try {
            const decoded = jwtDecode<JwtPayload>(token);
            if (!decoded.exp) return false;
            return decoded.exp < Date.now() / 1000;
        } catch (error) {
            return true;
        }
    }

    private checkTokenValidity(): boolean {
        const token = localStorage.getItem('token');
        if (!token) return false;
        return !this.isTokenExpired(token);
    }

    private getInitialUsername(): string | null {
        const token = this.getToken();
        if (!token || this.isTokenExpired(token)) {
            return null;
        }
        try {
            // const decoded = jwtDecode<UserPayload>(token);
            const userName = localStorage.getItem('userName');
            return userName || null;
        } catch (error) {
            return null;
        }
    }

    public getRoles(): string[] {
        const token = this.getToken();
        if (!token || this.isTokenExpired(token)) {
            return [];
        }
        try {
            const decoded = jwtDecode<UserPayload>(token);
            return decoded.roles ?? [];
        } catch (error) {
            return [];
        }
    }

    public checkRole(targetRole: string): boolean {
        const roles = this.getRoles();
        return roles.includes(targetRole);
    }

    public handleLoginSuccess(token: string, roles: string[]): void {
        localStorage.setItem('token', token);
        this.updateLoginStatus(token);
        this.roleSubject.next(roles);
        this.userNameSubject.next(localStorage.getItem("userName"));
    }

    public updateLoginStatus(token: string | null): void {
        const isValid = token !== null && !this.isTokenExpired(token!);
        // 呼叫 next()，通知所有訂閱者狀態發生了改變
        this.isLoggedInSubject.next(isValid);

        if (isValid) {
            try {
                const decoded = jwtDecode<UserPayload>(token!);
                const userName = decoded.userName || '使用者';
                this.userNameSubject.next(userName);
            } catch (error) {
                this.userNameSubject.next(null);
            }
        } else {
            this.userNameSubject.next(null); // 登出或無效時，清空名稱
        }
    }

    public logout(): Observable<ApiResponse<void>> {
        return this.http.post<ApiResponse<void>>(
            `${this.apiUrl}/auth/logout`, {}).pipe(
                tap((response) => {
                    console.log(response.message);
                    localStorage.removeItem('token');
                    localStorage.removeItem('userName');
                    this.updateLoginStatus(null); // 通知狀態變為登出
                })
            );
    }

}