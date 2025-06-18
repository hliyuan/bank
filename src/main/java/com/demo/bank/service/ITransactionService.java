package com.demo.bank.service;

import com.demo.bank.dto.TransactionAddRequest;
import com.demo.bank.dto.TransactionDTO;

import java.util.List;
import java.util.UUID;

public interface ITransactionService {

    /**
     * createTransaction
     *
     * @param addRequest
     * @return id
     */
    Long createTransaction(TransactionAddRequest addRequest);

    /**
     * getTransactionById
     *
     * @param id
     * @return TransactionDTO
     */
    TransactionDTO getTransactionById(Long id);

    /**
     * getAllTransactions
     *
     * @return List<TransactionDTO>
     */
    List<TransactionDTO> getAllTransactions();

    /**
     * deleteTransaction
     *
     * @param id
     */
    void deleteTransaction(Long id);

    /**
     * updateTransaction
     *
     * @param id
     * @param transactionDTO
     * @return TransactionDTO
     */
    TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO);

    /**
     * getTransactionsByAccountNumber
     *
     * @param accountNumber
     * @return List<TransactionDTO>
     */
    List<TransactionDTO> getTransactionsByAccountNumber(String accountNumber);

}
