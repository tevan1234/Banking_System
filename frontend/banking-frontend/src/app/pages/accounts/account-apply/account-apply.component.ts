import { Component, OnInit } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { CustomerService } from 'app/services/customer.service';
import { AccountService } from 'app/services/account.service';
import { Customer } from 'app/models/customer.model';
import { UserService } from 'app/services/user.service';
import { Observable, filter, take } from 'rxjs';
import { AuthService } from 'app/services/auth.service';
import { UserDetail } from 'app/models/user.model';


export interface CustomerInfo {
    customerId: number;
    fullName: string;
    idNumber: string;
    dob: string;
    phone: string;
    address: string;
    branch: string;
    kycStatus: 'PENDING' | 'VERIFIED' | 'REJECTED';
}

interface AccountFormData {
    // 第二頁：帳戶與卡片
    accountType: 'SAVINGS' | 'CHECKING' | '';
    branch: string;
    cardType: 'DEBIT' | 'CREDIT' | '';
    cardBrand: 'VISA' | 'MASTERCARD' | 'JCB' | '';
    cardHolder: string;
    currency: string;
}

@Component({
    selector: 'apply-account',
    standalone: true,
    imports: [CommonModule, NgClass, FormsModule, RouterModule],
    templateUrl: './account-apply.component.html',
    styleUrls: ['./account-apply.component.css']
})
export class AccountApplyComponent implements OnInit {
    customer: CustomerInfo | null = null;
    currentStep = 1;
    isLoading = true;

    constructor(private authService: AuthService, private userService: UserService, private accountService: AccountService, private router: Router) {

    }

    formData: AccountFormData = {
        accountType: '',
        branch: '',
        cardType: '',
        cardBrand: '',
        cardHolder: '',
        currency: 'TWD',
    };

    cardBrands = [
        { value: 'VISA', label: 'VISA', icon: 'assets/img/VISA.svg' },
        { value: 'MASTERCARD', label: 'Mastercard', icon: 'assets/img/MASTERCARD.svg' },
        { value: 'JCB', label: 'JCB', icon: 'assets/img/JCB.svg' }
    ];

    ngOnInit(): void {
        this.authService.userId$.pipe(
            filter(id => !!id),
            take(1)
        ).subscribe(id => {
            this.userService.getUserDetail(id!).subscribe({
                next: (res) => {
                    this.isLoading = false;
                    if (res.success && res.data) {
                        const u = res.data as UserDetail;
                        this.customer = {
                            customerId: u.customer?.customerId ?? 0,
                            fullName: u.fullName,
                            idNumber: u.customer?.idNumber ?? '',
                            dob: u.customer?.dob ?? '',
                            phone: u.phone ?? '',
                            address: u.customer?.address ?? '',
                            branch: u.customer?.branch ?? '',
                            kycStatus: u.customer?.kycStatus ?? 'PENDING',
                        };
                    }
                },
                error: () => {
                    this.isLoading = false;
                }
            });
        })
    }

    isStep2Valid(): boolean {
        return !!(
            this.formData.accountType &&
            this.formData.cardType &&
            this.formData.cardBrand &&
            this.formData.currency
        );
    }

    nextStep(): void {
        if (this.customer?.kycStatus === 'VERIFIED') {
            this.currentStep = 2;
        }
    }

    prevStep(): void {
        this.currentStep = 1;
    }

    submitForm(): void {
        if (!this.isStep2Valid() || !this.customer) return;

        const payload = {
            customerId: this.customer.customerId,
            accountType: this.formData.accountType,
            cardType: this.formData.cardType,
            cardBrand: this.formData.cardBrand,
            currency: this.formData.currency,
        };

        console.log('開戶申請資料：', payload);
        // TODO: this.accountService.openAccount(payload).subscribe(...)
    }
}
