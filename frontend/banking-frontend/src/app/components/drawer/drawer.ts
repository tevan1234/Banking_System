import { Component, OnDestroy, OnInit } from "@angular/core";
import { RouterModule, Router, NavigationEnd } from '@angular/router';
import { AsyncPipe, CommonModule, NgClass } from '@angular/common';
import { DrawerService } from "app/services/drawer.service";
import { AuthService } from "app/services/auth.service";
import { ApiResponse } from 'app/models/api-response.model';
import { Observable, Subscription } from "rxjs";

type DropdownKey = 'users' | 'customers' | 'products' | 'reports';

@Component({
    selector: 'app-drawer',
    standalone: true,
    templateUrl: './drawer.html',
    styleUrls: ['./drawer.css'],
    imports: [RouterModule, NgClass, CommonModule, AsyncPipe]
})
export class Drawer implements OnInit, OnDestroy {
    isLoggedIn$: Observable<boolean>;
    isDrawerOpen$: Observable<boolean>;
    private routerSub!: Subscription;
    roles$: Observable<string[]>;

    constructor(private drawerService: DrawerService, private authService: AuthService, private router: Router) {
        this.isLoggedIn$ = this.authService.isLoggedIn$;
        this.isDrawerOpen$ = this.drawerService.isDrawerOpen$;
        this.isDrawerOpen$.subscribe(state => {
            console.log('*** Drawer Component received final state:', state);
        });
        this.roles$ = this.authService.role$;
    }

    ngOnInit(): void {
        this.routerSub = this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.closeDrawer();
            }
        });
    }

    ngOnDestroy(): void {
        if (this.routerSub) {
            this.routerSub.unsubscribe();
        }
    }

    closeDrawer() {
        this.drawerService.close();
    }

    dropdownStates: Record<DropdownKey, boolean> = {
        users: false,
        customers: false,
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

    getRole(): string[] {
        return this.authService.getRoles();
    }

    logout(): void {
        this.authService.logout().subscribe({
            next: () => {
                this.closeDrawer();
                this.router.navigate(['/']);
            },
            error: err => console.error(err)
        });
    }
}