package com.iprzd.zshop.http.request;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PayCardRequest implements Serializable {

    private final static long serialVersionUID = 1540362525999L;

    private long id;
    private long uid;
    private int count;
    private String note;
    private long denomination;
    private int exprityMonth;
    private long amount;
    private String customer;
    private String customerPhone;

    @Override
    public String toString() {
        return "id:" + id + ";uid:" + uid + ";count:" + count + ";note:" + note + ";denomination:" + denomination
                + ";exprityMonth:" + exprityMonth + ";amount:" + amount + ";customer:" + customer + ";customerPhone:"
                + customerPhone;
    }
}