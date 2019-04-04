package com.connected.accountservice.infrastructure.database;

public class DatabaseConfig {

    public static final String DATABASE_DIRECTORY = "./";

    public static final String DATABASE_NAME = "account-service";

    public static final String DATABASE_URL = String.format("jdbc:h2:file:%s%s",
            DATABASE_DIRECTORY, DATABASE_NAME);

    private DatabaseConfig() {

    }
}
