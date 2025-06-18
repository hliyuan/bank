package com.demo.bank.dto;


import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 交易请求
 */
@Data
public class TransactionAddRequest {

    /**
     * 账号
     */
    private String accountNumber;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 交易类型：1.存钱 2.取钱 3.转账
     */
    private Integer type;
    /**
     * 交易描述
     */
    private String description;


    public boolean illegal(){
        return StringUtils.isBlank(accountNumber)
                || amount == null
                || amount.compareTo(BigDecimal.ZERO) <= 0
                || type == null;
    }
}
