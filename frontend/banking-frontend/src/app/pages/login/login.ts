import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { Router } from '@angular/router';
import { UserService } from 'app/services/user.service';
import { LoginService } from 'app/services/login.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [ReactiveFormsModule],
    templateUrl: './login.html'
})
export class login{
    loginForm: FormGroup;

    constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {
        this.loginForm = this.fb.group({
            userName: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    onSubmit() {
        if (this.loginForm.valid) {
            const request = { user: this.loginForm.value };
            this.loginService.login(request).subscribe({
                next: res => {
                    console.log('登入成功', res)
                    if(res.success) {
                        const token = res.data.token;
                        const userCert = res.data.userCert;

                        localStorage.setItem("token",token);
                        localStorage.setItem("userId",userCert.userId.toString());
                        localStorage.setItem("userName",userCert.userName);
                        localStorage.setItem("role",userCert.role);

                        this.router.navigate(['/']);
                    } else{
                        const errorMessage = res.message || '帳號或密碼錯誤，請重新輸入。';
                        console.warn('登入失敗',res);
                        alert(errorMessage);
                    }
                },
                error: err => console.error('登入失敗，無法辨識錯誤類型', err)
            });
        }
    }
}