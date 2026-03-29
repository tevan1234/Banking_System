import { AccountDetail } from "./account.model";

export enum KycStatus {
    PENDING = "PENDING",
    VERIFIED = "VERIFIED",
    REJECTED = "REJECTED"
}

export interface Customer {
    customerId: number;
    userId: number;
    idNumber: string;
    dob?: string;
    address?: string;
    branch: string;
    kycStatus: KycStatus;
}

export interface CustomerDetail extends Customer {
    accounts: AccountDetail[]
}

export interface CustomerApplyRequest {
    idNumber: string;
    dob: string;
    address: string;
    branch: string;
}