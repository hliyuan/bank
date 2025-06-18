package com.demo.bank.utils;

import com.demo.bank.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


public class SnowflakeIdGeneratorTest {


    SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1L);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionById_NotFound() {

        long id = snowflakeIdGenerator.nextId();
        assert  id > 0;
    }
}
