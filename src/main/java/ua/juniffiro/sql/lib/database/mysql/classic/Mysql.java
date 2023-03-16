package ua.juniffiro.sql.lib.database.mysql.classic;

import ua.juniffiro.sql.lib.database.Database;
import ua.juniffiro.sql.lib.database.DatabaseConfig;
import ua.juniffiro.sql.lib.database.DatabaseConnection;
import ua.juniffiro.sql.lib.utils.DbUtils;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class Mysql extends Database {

    /*
    Mysql database implementation.
     */

    private final DatabaseConnection connection;

    public Mysql(DatabaseConfig config) {
        super(config);
        this.connection = new ClassicConnection(DbUtils.getDriverUrl(
                config.getDatabaseType(),
                config.getHost(),
                config.getPort(),
                config.getDatabaseName(),
                config.getParams()),
                config.getUser(),
                config.getPassword());
    }

    @Override
    public DatabaseConnection getConnection() {
        return this.connection;
    }
}
