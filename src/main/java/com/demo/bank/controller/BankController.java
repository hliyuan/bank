package com.demo.bank.controller;


import com.demo.bank.dto.TransactionAddRequest;
import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.dto.WebResponseVo;
import com.demo.bank.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * transactions api
 */
@RestController
@RequestMapping("/api/transactions")
public class BankController {

    private final ITransactionService transactionService;


    public BankController(@Autowired @Qualifier("transactionService") ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * create transaction
     * @param request
     * @return id
     */
    @PostMapping
    public WebResponseVo<Long> createTransaction(@RequestBody TransactionAddRequest request) {

        Long id = transactionService.createTransaction(request);
        return WebResponseVo.buildData(id);
    }

    /**
     * getTransactionById
     *
     * @param id
     * @return TransactionDTO
     */
    @GetMapping("/{id}")
    public WebResponseVo<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        return WebResponseVo.buildData(transactionDTO);
    }

    /**
     * getAllTransactions
     *
     * @return List<TransactionDTO>
     */
    @GetMapping
    public WebResponseVo<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return WebResponseVo.buildData(transactions);
    }

    /**
     * getTransactionsByAccountNumber
     *
     * @param accountNumber
     * @return List<TransactionDTO>
     */
    @GetMapping("/account/{accountNumber}")
    public WebResponseVo<List<TransactionDTO>> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return WebResponseVo.buildData(transactions);
    }

    /**
     *
     * @param id
     * @param transactionDTO
     * @return TransactionDTO
     */
    @PutMapping("/{id}")
    public WebResponseVo<TransactionDTO> updateTransaction(
            @PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
        return WebResponseVo.buildData(updatedTransaction);
    }

    /**
     * deleteTransaction
     * @param id
     * @return id
     */
    @DeleteMapping("/{id}")
    public WebResponseVo<Long> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return WebResponseVo.buildData(id);
    }
}
