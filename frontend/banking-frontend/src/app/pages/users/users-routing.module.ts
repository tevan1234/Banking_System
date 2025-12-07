import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { UserListComponent } from './user-list/user-list.component';
import { UserAddComponent } from 'app/pages/users/user-add/user-add.component';
// import { UserEditComponent } from './user-edit/user-edit.component';
// import { UserDetailComponent } from './user-detail/user-detail.component';

const routes: Routes = [
    //   { path: '', component: UserListComponent },           // /users
    { path: 'add', component: UserAddComponent },         // /users/add
    //   { path: ':id/edit', component: UserEditComponent },   // /users/123/edit
    //   { path: ':id', component: UserDetailComponent }       // /users/123
];

export class UsersRoutingModule { }