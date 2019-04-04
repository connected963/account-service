package com.connected.accountservice.infrastructure.database.jdbi;

import org.jdbi.v3.core.Jdbi;

public class JdbiInjector {

    private JdbiInjector() {

    }

    public static Jdbi inject() {
        return JdbiInstance.JDBI_INSTANCE;
    }

    private static class JdbiInstance {
        private static final Jdbi JDBI_INSTANCE = JdbiConfig.createJdbi();
    }
}
