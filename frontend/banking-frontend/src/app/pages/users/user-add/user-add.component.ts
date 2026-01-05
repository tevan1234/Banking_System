import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { UserService } from 'app/services/user.service';
import { userRoleService } from 'app/services/userRole.service';
import { CreateUserRoleRequest, userRole } from 'app/models/userRole.model';
import { Router, RouterLink } from '@angular/router';
import { switchMap, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
    selector: 'app-add-user',
    standalone: true,              // Standalone Component
    imports: [ReactiveFormsModule, RouterLink],  // 匯入 ReactiveFormsModule
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.css']
})
export class UserAddComponent {
    userForm: FormGroup;

    constructor(private fb: FormBuilder, private userService: UserService, private userRoleService: userRoleService, private router: Router) {
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
            this.userService.addUser(request).pipe(
                switchMap(res => {
                    if (res.success) {
                        const userName = res.data.userName;
                        return this.userService.searchByUserName(userName);
                    } else {
                        console.warn('新增失敗，原因:', res.message);
                        return throwError(() => new Error(res.message || '新增使用者失敗'));
                    }
                }),
                switchMap(userRes => {
                    if (userRes.success) {
                        const foundUser = userRes.data;
                        const userId = foundUser.userId;
                        console.log('--- 成功取得並使用 User Model 屬性 ---');
                        console.log('使用者 ID:', foundUser.userId);
                        const CreateUserRoleRequest: CreateUserRoleRequest = {
                            userId: userId
                        };
                        return this.userRoleService.addUserRole({ userRole: CreateUserRoleRequest });
                    } else {
                        console.warn('查無使用者，新增角色權限失敗')
                        return throwError(() => new Error('查無使用者，新增角色權限失敗'))
                    }
                })
            ).subscribe({
                next: roleRes => {
                    console.log('所有操作成功：使用者新增完成並分配角色！', roleRes);
                    this.router.navigate(['/']);
                },
                error: err => {
                    console.error('新增使用者或角色分配失敗', err);
                    alert(`操作失敗：${err.message || '連線錯誤'}`);
                }
            });
        }
    }
}
