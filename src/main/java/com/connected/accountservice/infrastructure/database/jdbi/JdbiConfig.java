package com.connected.accountservice.infrastructure.database.jdbi;

import com.connected.accountservice.infrastructure.database.DatabaseConfig;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JdbiConfig {

    private JdbiConfig() {

    }

    static Jdbi createJdbi() {
        final var jdbi = Jdbi.create(DatabaseConfig.DATABASE_URL);

        installPlugins(jdbi);

        return jdbi;
    }

    private static void installPlugins(final Jdbi jdbi) {
        jdbi.installPlugin(new SqlObjectPlugin());
    }

}
