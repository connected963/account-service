package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.infrastructure.database.flyway.FlywayConfig;
import com.connected.accountservice.infrastructure.database.jdbi.JdbiInjector;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseRepositoryTest {

    protected Jdbi jdbi;

    protected BaseRepositoryTest() {
        jdbi = JdbiInjector.inject();
    }

    @BeforeAll
    static void runMigrations() {
        FlywayConfig.runMigrations();
    }

    @BeforeEach
    void truncateTables() {
        jdbi.useHandle(handle -> {
            handle.createUpdate("truncate table movement").execute();
            handle.createUpdate("truncate table account").execute();
        });
    }

}
