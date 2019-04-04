package com.connected.accountservice.infrastructure.database.flyway;

import com.connected.accountservice.infrastructure.database.DatabaseConfig;
import org.flywaydb.core.Flyway;

public class FlywayConfig {

    private FlywayConfig() {

    }

    public static void runMigrations() {
        final var flyway = createFlywayInstance();

        flyway.migrate();
    }

    private static Flyway createFlywayInstance() {
        return Flyway.configure()
                .dataSource(DatabaseConfig.DATABASE_URL, null, null)
                .load();
    }

}
