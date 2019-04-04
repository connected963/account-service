package com.connected.accountservice.infrastructure.database.utils;

import com.connected.accountservice.infrastructure.database.DatabaseConfig;
import org.h2.tools.DeleteDbFiles;

public class DataBaseUtils {

    private DataBaseUtils() {

    }

    public static void DropDatabase() {
        DeleteDbFiles.execute(DatabaseConfig.DATABASE_DIRECTORY,
                DatabaseConfig.DATABASE_NAME, Boolean.TRUE);
    }

}
