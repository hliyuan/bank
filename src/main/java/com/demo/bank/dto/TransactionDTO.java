package com.demo.bank.dto;

import com.demo.bank.model.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionDTO {

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

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setType(type);
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    public static TransactionDTO convertFromTransaction(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAccountNumber(transaction.getAccountNumber());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setTimestamp(transaction.getTimestamp());
        return transactionDTO;
    }
}
