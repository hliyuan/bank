package com.demo.bank.dto;

import com.demo.bank.model.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionDTO {

    private Long id;

    private String accountNumber;

    private BigDecimal amount;

    private Integer type;

    private String description;

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
