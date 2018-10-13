export interface AddressResponse {
    id?: number;
    userId?: number;
    isDefault: number;
    province: string;
    city: string;
    county: string;
    detail: string;
    contactName: string;
    phone: string;
}

export interface AddressRequet {
    id?: number;
    userId?: number;
    isDefault: number;
    province: string;
    city: string;
    county: string;
    detail: string;
    contactName: string;
    phone: string;
}
