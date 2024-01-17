package com.lab.batch;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseTemplate {
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    void setUp() {
        this.jobRepositoryTestUtils.removeJobExecutions();
    }
    
    @AfterEach
    void tearDown() {
        this.jobRepositoryTestUtils.removeJobExecutions();
    }
}
