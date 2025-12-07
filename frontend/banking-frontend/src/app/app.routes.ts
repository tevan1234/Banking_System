import { Routes } from '@angular/router';
import { UserAddComponent } from 'app/pages/users/user-add/user-add.component';
import { UserListComponent } from 'app/pages/users/user-list/user-list.component';
import { login } from 'app/pages/login/login'

export const routes: Routes = [
    { path: 'add-user', component: UserAddComponent },
    { path: 'user-list', component: UserListComponent },
    { path : 'login', component: login}
];
