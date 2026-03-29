import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';
import { ColDef, GridReadyEvent, ModuleRegistry, AllCommunityModule } from 'ag-grid-community';
import { Observable, BehaviorSubject, Subscription, filter, take } from "rxjs";
import { UserDetail, UserProfile } from 'app/models/user.model';
import { UserService } from 'app/services/user.service';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from 'app/services/auth.service';
import { CustomerService } from 'app/services/customer.service';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
    selector: 'app-user-detail',
    standalone: true,
    imports: [ReactiveFormsModule, AgGridModule, FormsModule, RouterModule],
    templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit {
    editOpen = false;
    userDetail: UserDetail | null = null;
    form: Partial<UserProfile> = {};
    // userProfile: UserProfile | null = null;
    constructor(private userService: UserService, private customerService: CustomerService, private authService: AuthService, private router: Router) { }

    ngOnInit(): void {
        this.authService.userId$.pipe(
            filter(id => !!id),
            take(1)
        ).subscribe(id => {
            this.userService.getUserDetail(id!).subscribe({
                next: res => {
                    if (res.success) {
                        console.log(res.data);
                        this.userDetail = res.data;
                        this.fillForm(this.userDetail);
                    }
                }
            })
        })
    }

    fillForm(data: UserDetail) {
        this.form = {
            userId: data.userId,
            fullName: data.fullName,
            email: data.email,
            phone: data.phone,
            idNumber: data.customer?.idNumber,
            dob: data.customer?.dob,
            address: data.customer?.address
        }
    }

    // openEditModal() {
    //     this.editOpen = true;
    // }

    updateProfile() {
        if (!this.form) return;

        this.userService.updateProfile(this.form).subscribe(res => {
            if (res.success) {
                this.userService.getUserDetail(this.userDetail!.userId).subscribe(detail => {
                    if(detail.success) {
                        this.userDetail = detail.data;
                        this.fillForm(detail.data);
                    }
                });
                this.editOpen = false;
            }
        });
    }
}