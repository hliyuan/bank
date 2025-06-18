package com.demo.bank.service.impl;

import com.demo.bank.dao.ITransactionDao;
import com.demo.bank.dto.TransactionAddRequest;
import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.exception.IllegalParamException;
import com.demo.bank.exception.ResourceNotFoundException;
import com.demo.bank.model.Transaction;
import com.demo.bank.service.ITransactionService;
import com.demo.bank.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("transactionService")
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionDao transactionDao;

    public TransactionServiceImpl(@Autowired @Qualifier("transactionDao") ITransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1); // 机器ID


    @Override
    @CacheEvict(value = "transactions", allEntries = true)
    public Long createTransaction(TransactionAddRequest addRequest) {
        if (addRequest == null || addRequest.illegal()) {
            throw new IllegalParamException("param illegal");
        }
        long id = generator.nextId();
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setDescription(addRequest.getDescription());
        transaction.setType(addRequest.getType());
        transaction.setAmount(addRequest.getAmount());
        transaction.setAccountNumber(addRequest.getAccountNumber());
        transaction.setTimestamp(System.currentTimeMillis());
        transactionDao.save(transaction);
        return id;
    }

    @Override
    @Cacheable(value = "transaction", key = "#id")
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionDao.findById(id);
        if (transaction == null) {
            throw new ResourceNotFoundException("Transaction", id.toString());
        }
        return TransactionDTO.convertFromTransaction(transaction);
    }

    @Override
    @Cacheable(value = "transactions")
    public List<TransactionDTO> getAllTransactions() {
        return transactionDao.listTransaction().stream()
                .map(transaction -> TransactionDTO.convertFromTransaction(transaction))
                .collect(Collectors.toList());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "transaction", key = "#id"),          // 只删除 transaction::[id]
            @CacheEvict(value = "transactions", allEntries = true)    // 清空整个 transactions 缓存区域
    })
    public void deleteTransaction(Long id) {
        if (!transactionDao.existsById(id)) {
            throw new ResourceNotFoundException("Transaction", id.toString());
        }
        transactionDao.deleteById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "transaction", key = "#id"),          // 只删除 transaction::[id]
            @CacheEvict(value = "transactions", allEntries = true)    // 清空整个 transactions 缓存区域
    })    public TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionDao.findById(id);
        if (existingTransaction == null) {
            throw new ResourceNotFoundException("Transaction", id.toString());
        }
        existingTransaction = transactionDTO.toTransaction();
        existingTransaction.setId(id);
        Transaction updatedTransaction = transactionDao.save(existingTransaction);
        return TransactionDTO.convertFromTransaction(updatedTransaction);
    }

    @Override
    @Cacheable(value = "transactions", key = "#accountNumber")
    public List<TransactionDTO> getTransactionsByAccountNumber(String accountNumber) {
        return transactionDao.findByAccountNumber(accountNumber).stream()
                .map(transaction -> TransactionDTO.convertFromTransaction(transaction))
                .collect(Collectors.toList());
    }
}
