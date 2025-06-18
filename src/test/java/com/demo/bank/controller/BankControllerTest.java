package com.demo.bank.controller;

import com.demo.bank.dto.TransactionAddRequest;
import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.dto.WebResponseVo;
import com.demo.bank.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BankControllerTest {

    @Mock
    private ITransactionService transactionService;

    @InjectMocks
    private BankController bankController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_Success() {
        TransactionAddRequest request = new TransactionAddRequest();
        request.setDescription("Test");
        request.setType(1);
        request.setAccountNumber("1234567890");

        when(transactionService.createTransaction(any(TransactionAddRequest.class))).thenReturn(1L);

        WebResponseVo<Long> response = bankController.createTransaction(request);

        assertNotNull(response);
        assertEquals(1L, response.getData());
        verify(transactionService, times(1)).createTransaction(any(TransactionAddRequest.class));
    }

    @Test
    void getTransactionById_Success() {
        TransactionDTO mockDto = new TransactionDTO();
        mockDto.setId(1L);
        mockDto.setDescription("Test");

        when(transactionService.getTransactionById(1L)).thenReturn(mockDto);

        WebResponseVo<TransactionDTO> response = bankController.getTransactionById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getData().getId());
        assertEquals("Test", response.getData().getDescription());
    }

    @Test
    void getAllTransactions_Success() {
        TransactionDTO dto1 = new TransactionDTO();
        dto1.setId(1L);
        TransactionDTO dto2 = new TransactionDTO();
        dto2.setId(2L);

        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(dto1, dto2));

        WebResponseVo<List<TransactionDTO>> response = bankController.getAllTransactions();

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }

    @Test
    void getAllTransactions_Empty() {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        WebResponseVo<List<TransactionDTO>> response = bankController.getAllTransactions();

        assertNotNull(response);
        assertTrue(response.getData().isEmpty());
    }

    @Test
    void getTransactionsByAccountNumber_Success() {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(1L);
        dto.setAccountNumber("1234567890");

        when(transactionService.getTransactionsByAccountNumber("1234567890"))
                .thenReturn(Collections.singletonList(dto));

        WebResponseVo<List<TransactionDTO>> response = bankController.getTransactionsByAccountNumber("1234567890");

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals("1234567890", response.getData().get(0).getAccountNumber());
    }

    @Test
    void updateTransaction_Success() {
        TransactionDTO updateDto = new TransactionDTO();
        updateDto.setDescription("Updated");

        TransactionDTO updatedDto = new TransactionDTO();
        updatedDto.setId(1L);
        updatedDto.setDescription("Updated");

        when(transactionService.updateTransaction(1L, updateDto)).thenReturn(updatedDto);

        WebResponseVo<TransactionDTO> response = bankController.updateTransaction(1L, updateDto);

        assertNotNull(response);
        assertEquals(1L, response.getData().getId());
        assertEquals("Updated", response.getData().getDescription());
    }

    @Test
    void deleteTransaction_Success() {
        doNothing().when(transactionService).deleteTransaction(1L);

        WebResponseVo<Long> response = bankController.deleteTransaction(1L);

        assertNotNull(response);
        assertEquals(1L, response.getData());
        verify(transactionService, times(1)).deleteTransaction(1L);
    }

    // 注意：如果取消注释fallback方法，可以添加以下测试
    /*
    @Test
    void fallback_NotFound() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/invalid/path");

        ResponseEntity<Map<String, Object>> response = bankController.fallback(mockRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
    }
    */
}
