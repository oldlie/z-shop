package com.iprzd.zshop.http.request;

import java.io.Serializable;

import com.iprzd.zshop.entity.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest implements Serializable {

    private final static long serialVersionUID = 1538612529913L;

    private long id;
    private long userId;
    private int isDefault;
    private String province;
    private String city;
    private String county;
    private String detail;
    private String contactName;
    private String phone;

    public Address toAddress() {
        Address address = new Address();
        address.setId(id);
        address.setUserId(userId);
        address.setIsDefault(isDefault);
        address.setProvince(province);
        address.setCity(city);
        address.setCounty(county);
        address.setDetail(detail);
        address.setContactName(contactName);
        address.setPhone(phone);
        return address;
    }
}