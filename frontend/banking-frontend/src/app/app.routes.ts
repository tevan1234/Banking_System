import { Routes } from '@angular/router';
import { UserAddComponent } from 'app/pages/users/user-add/user-add.component';
import { UserListComponent } from 'app/pages/users/user-list/user-list.component';
import { UserDetailComponent } from 'app/pages/users/user-detail/user-detail.component';
import { CustomerApplyComponent } from 'app/pages/customers/customer-apply/customer-apply.component';
import { login } from 'app/pages/login/login'
import { AccountApplyComponent } from './pages/accounts/account-apply/account-apply.component';

export const routes: Routes = [
    { path: 'add-user', component: UserAddComponent },
    { path: 'apply-customer', component: CustomerApplyComponent },
    { path: 'apply-account', component: AccountApplyComponent },
    { path: 'user-list', component: UserListComponent },
    { path: 'user-detail', component: UserDetailComponent },
    { path : 'login', component: login}
];
