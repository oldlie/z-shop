export interface Address {
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

export interface AddressResponse extends Address {
    _?: String;
}

export interface AddressRequet extends Address {
    _?: String;
}
