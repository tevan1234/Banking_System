import { Component, OnInit } from "@angular/core";
import { RouterModule, Router } from '@angular/router';
import { AsyncPipe, CommonModule, NgClass } from '@angular/common';
import { DrawerService } from "app/services/drawer.service";
import { AuthService } from "app/services/auth.service";
import { Observable } from "rxjs";

type DropdownKey = 'users' | 'customers' | 'products' | 'reports';

@Component({
    selector: 'app-drawer',
    standalone: true,
    templateUrl: './drawer.html',
    styleUrls: ['./drawer.css'],
    imports: [RouterModule,NgClass, CommonModule, AsyncPipe]
})
export class Drawer implements OnInit {

    isDrawerOpen$: Observable<boolean>;
    role$: Observable<string | null>;

    constructor(private drawerService: DrawerService, private authService: AuthService, private router: Router) {
        this.isDrawerOpen$ = this.drawerService.isDrawerOpen$;
        this.isDrawerOpen$.subscribe(state => {
            console.log('*** Drawer Component received final state:', state);
        });
        this.role$ = this.authService.role$;
    }

    ngOnInit(): void { }

    closeDrawer() {
        this.drawerService.close();
    }

    dropdownStates: Record<DropdownKey, boolean> = {
        users: false,
        customers:false,
        products: false,
        reports: false
    };

    toggleDropdown(key: DropdownKey): void {
        const currentState = this.dropdownStates[key];
        this.dropdownStates[key] = !currentState;

        (Object.keys(this.dropdownStates) as DropdownKey[]).filter(k => k !== key).forEach(k => {
            if (this.dropdownStates[k] === true) {
                this.dropdownStates[k] = false
            }
        });
    }

    isDropdownOpen(key: DropdownKey): boolean {
        return this.dropdownStates[key];
    }
    
    getRole() : string | null {
        return this.authService.getRole();
    }

    logout():void {
        this.authService.logout();
        this.router.navigate(['/']);
    }
}