package com.connected.accountservice.infrastructure.repository;

import com.connected.accountservice.infrastructure.database.DatabaseConfig;
import com.connected.accountservice.infrastructure.database.flyway.FlywayConfig;
import com.connected.accountservice.infrastructure.database.jdbi.JdbiInjector;
import org.h2.tools.DeleteDbFiles;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterAll;
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

    @AfterAll
    static void dropDatabase() {
        DeleteDbFiles.execute(DatabaseConfig.DATABASE_DIRECTORY,
                DatabaseConfig.DATABASE_NAME, Boolean.TRUE);
    }

    @BeforeEach
    void truncateTables() {
        jdbi.useHandle(handle -> {
            handle.createUpdate("truncate table movement").execute();
            handle.createUpdate("truncate table account").execute();
        });
    }

}
