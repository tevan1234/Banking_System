import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { UserService } from 'app/services/user.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-add-user',
    standalone: true,              // Standalone Component
    imports: [ReactiveFormsModule],  // 匯入 ReactiveFormsModule
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.css']
})
export class UserAddComponent {
    userForm: FormGroup;

    constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
        this.userForm = this.fb.group({
            userName: ['', Validators.required],
            password: ['', Validators.required],
            fullName: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            phone: ['']
        });
    }

    onSubmit() {
        if (this.userForm.valid) {
            const request = { user: this.userForm.value };
            this.userService.addUser(request).subscribe({
                next: res => {
                    console.log('新增成功', res)
                    if (res.success) {
                        // 這裡做跳轉
                        this.router.navigate(['/user-list']);
                    } else {
                        // 或顯示錯誤訊息、不跳轉
                        console.warn('後端回傳失敗，無法跳轉', res);
                    }
                },
                error: err => console.error('新增失敗', err)
            });
        }
    }
}
