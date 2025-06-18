package com.demo.bank.dao;

import com.demo.bank.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface ITransactionDao {

    /**
     * save transaction
     *
     * @param transaction
     * @return
     */
    Transaction save(Transaction transaction);

    /**
     * listTransaction
     *
     * @return
     */
    List<Transaction> listTransaction();

    /**
     * findById
     *
     * @param id
     * @return
     */
    Transaction findById(Long id);

    /**
     * deleteById
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * existsById
     *
     * @param id
     * @return
     */
    boolean existsById(Long id);

    /**
     * findByAccountNumber
     *
     * @param accountNumber
     * @return
     */
    List<Transaction> findByAccountNumber(String accountNumber);
}
