package com.demo.bank.enums;


import lombok.Getter;

/**
 * 交易类型枚举
 */
public enum TransactionType {

    DEPOSIT(1),
    WITHDRAWAL(2),
    TRANSFER(3);

    @Getter
    private int code;

    TransactionType(int code) {
        this.code = code;
    }
}
