import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CustomerService } from 'app/services/customer.service';
import { CustomerApplyRequest } from 'app/models/customer.model';

@Component({
    selector: 'apply-customer',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, RouterModule],
    templateUrl: './customer-apply.component.html',
    styleUrls: ['./customer-apply.component.css']
})
export class CustomerApplyComponent implements OnInit {

    applyForm!: FormGroup;
    isSubmitting = false;
    submitSuccess = false;
    errorMessage = '';

    readonly twDistricts: Record<string, string[]> = {
        '台北市': ['中正區', '大同區', '中山區', '松山區', '大安區', '萬華區', '信義區', '士林區', '北投區', '內湖區', '南港區', '文山區'],
        '新北市': ['板橋區', '三重區', '中和區', '永和區', '新莊區', '新店區', '樹林區', '鶯歌區', '三峽區', '淡水區'],
        '桃園市': ['桃園區', '中壢區', '大溪區', '楊梅區', '蘆竹區', '大園區', '龜山區', '八德區', '龍潭區', '平鎮區'],
        '台中市': ['中區', '東區', '南區', '西區', '北區', '北屯區', '西屯區', '南屯區', '太平區', '大里區'],
        '台南市': ['中西區', '東區', '南區', '北區', '安平區', '安南區', '永康區', '歸仁區', '新化區', '左鎮區'],
        '高雄市': ['楠梓區', '左營區', '鼓山區', '三民區', '鹽埕區', '前金區', '苓雅區', '前鎮區', '旗津區', '小港區'],
    };

    readonly branches = [
        '台北總行',
        '台北分行',
        '新北分行',
        '桃園分行',
        '台中分行',
        '高雄分行',
    ];

    // 台灣身分證驗證（標準加權演算法）
    static twIdValidator(control: AbstractControl): { [key: string]: boolean } | null {
        const value: string = control.value?.trim().toUpperCase() ?? '';
        if (!value) return null;

        const letterMap: Record<string, number> = {
            A: 10, B: 11, C: 12, D: 13, E: 14, F: 15, G: 16, H: 17, I: 34, J: 18,
            K: 19, L: 20, M: 21, N: 22, O: 35, P: 23, Q: 24, R: 25, S: 26, T: 27,
            U: 28, V: 29, W: 32, X: 30, Y: 31, Z: 33
        };

        if (!/^[A-Z][12][0-9]{8}$/.test(value)) return { twId: true };

        const n = letterMap[value[0]];
        const digits = [Math.floor(n / 10), n % 10, ...value.slice(1).split('').map(Number)];
        const weights = [1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1];
        const sum = digits.reduce((acc, d, i) => acc + d * weights[i], 0);

        return sum % 10 === 0 ? null : { twId: true };
    }

    constructor(
        private fb: FormBuilder,
        private router: Router,
        private customerService: CustomerService
    ) { }

    ngOnInit(): void {
        this.applyForm = this.fb.group({
            idNumber: ['', [
                Validators.required,
                Validators.pattern(/^[A-Z][12][0-9]{8}$/),
                CustomerApplyComponent.twIdValidator
            ]],
            dob: ['', [Validators.required]],
            city: ['', Validators.required],
            district: ['', Validators.required],
            streetDetail: ['', [Validators.required, Validators.minLength(4)]],
            branch: ['', Validators.required],
            consent: [false, Validators.requiredTrue]
        });

        this.applyForm.get('city')?.valueChanges.subscribe(() => {
            this.applyForm.get('district')?.setValue('');
        });
    }

    get f() {
        return this.applyForm.controls;
    }

    get cities(): string[] {
        return Object.keys(this.twDistricts);
    }

    get availableDistricts(): string[] {
        const city = this.applyForm.get('city')?.value;
        return city ? this.twDistricts[city] : [];
    }

    getFieldError(field: string): string {
        const control = this.applyForm.get(field);
        if (!control || !control.touched || !control.errors) return '';

        if (field === 'idNumber') {
            if (control.errors['required']) return '必填欄位';
            if (control.errors['pattern'] || control.errors['twId']) return '身分證格式錯誤（例：A123456789）';
        }
        if (field === 'dob') {
            if (control.errors['required']) return '必填欄位';
        }
        if (field === 'address') {
            if (control.errors['required']) return '必填欄位';
            if (control.errors['minlength']) return '地址至少需填寫 4 個字';
        }
        if (field === 'branch') {
            if (control.errors['required']) return '請選擇分行';
        }
        return '';
    }

    onSubmit(): void {
        this.applyForm.markAllAsTouched();
        if (this.applyForm.invalid) return;

        this.isSubmitting = true;
        this.errorMessage = '';
        const { city, district, streetDetail } = this.applyForm.value;
        const payload: CustomerApplyRequest = {
            idNumber: this.applyForm.value.idNumber.trim().toUpperCase(),
            dob: this.applyForm.value.dob,
            address: `${city}${district}${streetDetail}`,
            branch: this.applyForm.value.branch,
        };
        console.log(JSON.stringify(payload));
        this.customerService.applyCustomer(payload).subscribe({
            next: (res) => {
                if (res.success) {
                    this.submitSuccess = true;
                } else {
                    this.errorMessage = res.message ?? '提交失敗，請稍後再試';
                }
                this.isSubmitting = false;
            },
            error: (err) => {
                this.errorMessage = err.error?.message ?? '提交失敗，請稍後再試';
                this.isSubmitting = false;
            }
        });

    }

    goHome(): void {
        this.router.navigate(['/']);
    }
}
