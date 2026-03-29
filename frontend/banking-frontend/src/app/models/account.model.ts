import { CardBrand, CardType } from "./card.model";

export enum AccountType {
    SAVINGS = "SAVINGS",
    CHECKING = "CHECKING"
}

export enum Status {
    ACTIVE = "ACTIVE",
    FROZEN = "FROZEN",
    CLOSED = "CLOSED"
}

export interface Account {
    accountId: number;
    customerId: number;
    accountNo: string;
    accountType: AccountType;
    branch: string;
    balance: number;
    currency: string;
    accountStatus: Status;
}

export interface AccountDetail {
    accountId: number;
    customerId: number;
}

export interface applyAccount {
    customerId: number;
    accountType: AccountType;
    branch: string;
    currency: string;
    cardType: CardType;
    cardBrand: CardBrand;
    cardHolder: string;
}