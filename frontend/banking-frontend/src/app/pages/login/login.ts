import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { RouterModule, Router } from '@angular/router';
import { AuthService } from 'app/services/auth.service';
import { UserService } from 'app/services/user.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [RouterModule, ReactiveFormsModule],
    templateUrl: './login.html'
})
export class login {
    loginForm: FormGroup;

    constructor(private fb: FormBuilder, private authService: AuthService, private userService: UserService, private router: Router) {
        this.loginForm = this.fb.group({
            userName: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    onSubmit() {
        localStorage.clear();
        if (this.loginForm.valid) {
            const request = this.loginForm.value ;
            this.authService.login(request).subscribe({
                next: res => {
                    console.log('登入成功', res)
                    if (res.success) {
                        const token = res.data.token;
                        const userCert = res.data.userCert;

                        this.authService.handleLoginSuccess(token, userCert);
                        this.router.navigate(['/']);
                    } else {
                        const errorMessage = res.message || '帳號或密碼錯誤，請重新輸入。';
                        console.warn('登入失敗', res);
                        alert(errorMessage);
                    }
                },
                error: (error) => {
                    console.error('登入失敗:', error);
                    console.log('錯誤狀態:', error.status);
                    console.log('錯誤內容:', error.error);

                    const errorMsg = error.error?.message || '登入失敗，請稍後再試';
                    alert(errorMsg);
                }
            });
        }
    }
}