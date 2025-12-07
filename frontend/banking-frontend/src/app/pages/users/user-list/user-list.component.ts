import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from 'app/services/user.service';
import { ApiResponse } from 'app/models/api-response.model';
import { Router } from '@angular/router';
import { User } from 'app/models/user.model';
import { AgGridModule } from 'ag-grid-angular';
import { ColDef, GridReadyEvent, ModuleRegistry, AllCommunityModule } from 'ag-grid-community';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
    selector: 'app-user-list',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, AgGridModule],
    templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
    columnDefs: ColDef<User>[] = [
        { field: 'userName', headerName: '使用者帳號' },
        { field: 'fullName', headerName: '姓名' },
        { field: 'email', headerName: 'Email' },
        {
            headerName: '詳情',
            cellRenderer: (params: any) => {
                return `<button class="btn btn-primary btn-sm" data-action="view">查看</button>`;
            }
        }
    ];

    onCellClicked(event: any) {
        const action = event.event.target?.dataset.action;

        if (action === 'view') {
            const id = event.data.userId;
            this.goToDetail(id);
        }
    }

    onGridReady(event: GridReadyEvent) {
        event.api.sizeColumnsToFit();
    }

    users: User[] = [];
    constructor(private userService: UserService, private router: Router) { }

    ngOnInit(): void {
        this.loadUsers();
    }

    loadUsers() {
        this.userService.getUsers().subscribe({
            next: (res: ApiResponse<User[]>) => {
                if (res.success) {
                    this.users = res.data;
                }
            }
        });
    }

    goToDetail(id: number | undefined) {
        this.router.navigate(['/user-detail', id!]);
    }

}