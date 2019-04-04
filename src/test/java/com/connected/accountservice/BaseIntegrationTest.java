package com.connected.accountservice;

import com.connected.accountservice.infrastructure.database.flyway.FlywayConfig;
import com.connected.accountservice.infrastructure.database.utils.DataBaseUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseIntegrationTest {

    @BeforeAll
    static void createApplicationEnvironment() {
        ApplicationTest.start();
    }

    @BeforeEach
    void runMigrations() {
        FlywayConfig.runMigrations();
    }

    @BeforeEach
    void dropDatabase() {
        DataBaseUtils.DropDatabase();
    }
}
