package com.demo.bank.dao.impl;

import com.demo.bank.dao.ITransactionDao;
import com.demo.bank.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service("transactionDao")
public class TransactionDaoImpl implements ITransactionDao {

    private final ConcurrentMap<Long, Transaction> transactions = new ConcurrentHashMap<>();

    @Override
    public Transaction save(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public List<Transaction> listTransaction() {
        return new ArrayList<>(transactions.values());
    }

    @Override
    public Transaction findById(Long id) {
        return transactions.get(id);
    }

    @Override
    public void deleteById(Long id) {
        transactions.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return transactions.containsKey(id);
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        return transactions.values().stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .toList();
    }
}
