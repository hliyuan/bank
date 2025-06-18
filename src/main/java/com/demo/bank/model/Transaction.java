package com.demo.bank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Transaction {

    /**
     * 交易id
     */
    private Long id;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 交易类型
     */
    private Integer type;

    /**
     * 交易描述
     */
    private String description;

    /**
     * 交易时间
     */
    private Long timestamp;
}
