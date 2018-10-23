package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_pay_card")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PayCardEntity implements Serializable {
    private static final long serialVersionUID = 1540124491325L;

    @Id @GeneratedValue
    private long id;
    @Column(columnDefinition = "tinyint comment '当前卡是否有效'")
    private int isValid;
    @Column(columnDefinition = "bigint comment '发卡人ID'")
    private long accountId;
    @Column(columnDefinition = "varchar(255) comment '发卡账号'")
    private String account;
    private String note;
    private Date createDate;
    @Column (columnDefinition = "int comment '有效月数'")
    private int expiryMonth;
    private String number;
    private String verifyCode;
    @Column(columnDefinition = "bigint comment '面额厘作为基准单位'")
    private long denomination;
    @Column(columnDefinition = "bigint comment '实际金额厘作为基准单位'")
    private long amount;
    private int isSoldOut;
    @Column(columnDefinition = "varchar(255) comment '购卡人'")
    private String customer;
    private String customerPhone;
    private int isUsed;
    private String user;
    private long userId;
    private Date useDate;
}
