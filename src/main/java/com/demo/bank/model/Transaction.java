package com.demo.bank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Transaction {

    private Long id;

    private String accountNumber;

    private BigDecimal amount;

    private Integer type;

    private String description;

    private Long timestamp;
}
