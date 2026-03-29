export enum CardType {
    DEBIT = "DEBIT",
    CREDIT = "CREDIT"
}

export enum CardBrand {
    VISA = "VISA",
    MASTERCARD = "MASTERCARD",
    JCB = "JCB",
    UNIONPAY = "UNIONPAY"
}

export enum Status {
    ACTIVE = "ACTIVE",
    BLOCKED = "BLOCKED",
    EXPIRED = "EXPIRED"
}

export interface Card {
    cardId: number;
    accountId: number;
    cardNumber: string;
    cardType: CardType;
    cardBrand: CardBrand;
    cardHolder: string;
    status: Status;
}

export interface CardApply {
    accountId: number; 
    cardType: CardType;
    cardBrand: CardBrand;
    cardHolder: string;
}