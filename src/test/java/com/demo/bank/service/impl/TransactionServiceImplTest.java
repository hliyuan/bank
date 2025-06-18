package com.demo.bank.service.impl;

import com.demo.bank.dao.ITransactionDao;
import com.demo.bank.dto.TransactionAddRequest;
import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.exception.IllegalParamException;
import com.demo.bank.exception.ResourceNotFoundException;
import com.demo.bank.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private ITransactionDao transactionDao;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_Success() {
        TransactionAddRequest request = new TransactionAddRequest();
        request.setDescription("Test transaction");
        request.setType(1);
        request.setAccountNumber("1234567890");
        request.setAmount(new BigDecimal(10.55));

        when(transactionDao.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction t = invocation.getArgument(0);
            t.setId(1L);
            return t;
        });

        Long transactionId = transactionService.createTransaction(request);

        assertNotNull(transactionId);
        verify(transactionDao, times(1)).save(any(Transaction.class));
    }

    @Test
    void createTransaction_IllegalParam() {
        assertThrows(IllegalParamException.class, () -> transactionService.createTransaction(null));

        TransactionAddRequest invalidRequest = new TransactionAddRequest();
        assertThrows(IllegalParamException.class, () -> transactionService.createTransaction(invalidRequest));
    }

    @Test
    void getTransactionById_Success() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setDescription("Test");

        when(transactionDao.findById(1L)).thenReturn(transaction);

        TransactionDTO result = transactionService.getTransactionById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test", result.getDescription());
    }

    @Test
    void getTransactionById_NotFound() {
        when(transactionDao.findById(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> transactionService.getTransactionById(1L));
    }

    @Test
    void getAllTransactions_Success() {
        Transaction t1 = new Transaction();
        t1.setId(1L);
        Transaction t2 = new Transaction();
        t2.setId(2L);

        when(transactionDao.listTransaction()).thenReturn(Arrays.asList(t1, t2));

        List<TransactionDTO> result = transactionService.getAllTransactions();

        assertEquals(2, result.size());
        verify(transactionDao, times(1)).listTransaction();
    }

    @Test
    void getAllTransactions_Empty() {
        when(transactionDao.listTransaction()).thenReturn(Collections.emptyList());

        List<TransactionDTO> result = transactionService.getAllTransactions();

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteTransaction_Success() {
        when(transactionDao.existsById(1L)).thenReturn(true);

        transactionService.deleteTransaction(1L);

        verify(transactionDao, times(1)).deleteById(1L);
    }

    @Test
    void deleteTransaction_NotFound() {
        when(transactionDao.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> transactionService.deleteTransaction(1L));
    }

    @Test
    void updateTransaction_Success() {
        Transaction existing = new Transaction();
        existing.setId(1L);

        when(transactionDao.findById(1L)).thenReturn(existing);
        when(transactionDao.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TransactionDTO updateDto = new TransactionDTO();
        updateDto.setDescription("Updated");
        updateDto.setType(1);
        updateDto.setAccountNumber("9876543210");

        TransactionDTO result = transactionService.updateTransaction(1L, updateDto);

        assertNotNull(result);
        assertEquals("Updated", result.getDescription());
        assertEquals(1, result.getType());
    }

    @Test
    void updateTransaction_NotFound() {
        when(transactionDao.findById(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> transactionService.updateTransaction(1L, new TransactionDTO()));
    }

    @Test
    void getTransactionsByAccountNumber_Success() {
        Transaction t1 = new Transaction();
        t1.setId(1L);
        t1.setAccountNumber("1234567890");

        when(transactionDao.findByAccountNumber("1234567890")).thenReturn(Arrays.asList(t1));

        List<TransactionDTO> result = transactionService.getTransactionsByAccountNumber("1234567890");

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void getTransactionsByAccountNumber_Empty() {
        when(transactionDao.findByAccountNumber(anyString())).thenReturn(Collections.emptyList());

        List<TransactionDTO> result = transactionService.getTransactionsByAccountNumber("1234567890");

        assertTrue(result.isEmpty());
    }
}