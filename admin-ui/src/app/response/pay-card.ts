
export interface PayCard {
    id?: number;
    isValid: number;
    accountId: number;
    account: string;
    note?: string;
    createDate: Date;
    expiryMonth: number;
    number?: number;
    verifyCode?: number;
    denomination: number;
    amount: number;
    isSoldOut?: number;
    customer?: string;
    customerPhone?: string;
    isUsed?: number;
    user?: string;
    userId?: number;
    useDate?: Date;
}

export interface PayCardCreateModel {
    id?: number;
    count: number;
    note: string;
    denomination: number;
    expiryMonth: number;
    amount: number;
    customer: string;
    customerPhone: string;
}
