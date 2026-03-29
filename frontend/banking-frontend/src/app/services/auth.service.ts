import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RouterModule, Router } from '@angular/router';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from 'app/models/api-response.model';
import { loginRequest, loginResponse, UserCert } from '../models/user.model';
import { jwtDecode, JwtPayload } from 'jwt-decode';

interface UserPayload extends JwtPayload {
    userId?: number;
    roles?: string[];
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = `${environment.apiUrl}`;
    private isLoggedInSubject = new BehaviorSubject<boolean>(this.checkTokenValidity());
    isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();
    private userNameSubject = new BehaviorSubject<string | null>(null);
    userName$: Observable<string | null> = this.userNameSubject.asObservable();
    private roleSubject = new BehaviorSubject<string[]>(this.getRoles());
    role$: Observable<string[]> = this.roleSubject.asObservable();
    private userIdSubject = new BehaviorSubject<number | null>(this.getUserId());
    userId$: Observable<number | null> = this.userIdSubject.asObservable();

    constructor(private http: HttpClient, private router: Router) { }

    login(request: loginRequest): Observable<ApiResponse<loginResponse>> {
        // return this.http.post<ApiResponse<loginResponse>>(`${this.apiUrl}/auth/login`, request);
        return this.http.post<ApiResponse<loginResponse>>(`/api/auth/login`, request);
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

    private getUserId(): number | null {
        const token = this.getToken();
        if (!token || this.isTokenExpired(token)) {
            return null;
        } try {
            const decoded = jwtDecode<UserPayload>(token);
            return decoded.sub ? Number(decoded.sub) : null;
        } catch (error) {
            return null;
        }
    }

    // private getInitialUsername(): string | null {
    //     const token = this.getToken();
    //     if (!token || this.isTokenExpired(token)) {
    //         return null;
    //     }
    //     try {
    //         const decoded = jwtDecode<UserPayload>(token);
    //         // const userName = localStorage.getItem('userName');
    //         // return this.userName$;
    //         return null;
    //     } catch (error) {
    //         return null;
    //     }
    // }

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

    public handleLoginSuccess(token: string, userCert: UserCert): void {
        localStorage.setItem('token', token);
        this.isLoggedInSubject.next(true);
        this.userIdSubject.next(userCert.userId);
        this.userNameSubject.next(userCert.userName);
        this.roleSubject.next(userCert.roles);
    }

    public updateLoginStatus(token: string | null): void {
        const isValid = token !== null && !this.isTokenExpired(token!);
        this.isLoggedInSubject.next(isValid);

        if (!isValid) {
            this.userNameSubject.next(null);
            this.userIdSubject.next(null);
            this.roleSubject.next([]);
        }
    }

    public setUserName(userName: string): void {
        this.userNameSubject.next(userName);
    }

    public logout(): Observable<ApiResponse<void>> {
        return this.http.post<ApiResponse<void>>(
            `${this.apiUrl}/auth/logout`, {}).pipe(
                tap((response) => {
                    console.log(response.message);
                    localStorage.removeItem('token');
                    this.updateLoginStatus(null); // 通知狀態變為登出
                })
            );
    }

}