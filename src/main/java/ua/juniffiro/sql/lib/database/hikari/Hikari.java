package ua.juniffiro.sql.lib.database.hikari;

import com.zaxxer.hikari.HikariConfig;
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
public class Hikari extends Database {

    /*
    Implementation of an abstract database using HikariPool.
     */

    private final HikariConfig hikariConfig;
    private final DatabaseConnection connection;

    public Hikari(DatabaseConfig config, HikariConfig hikariConfig) {
        super(config);

        this.hikariConfig = hikariConfig;
        configure(config.getDatabaseType());
        this.connection = new HikariPoolConnection(hikariConfig);
    }

    public HikariConfig getHikariConfig() {
        return hikariConfig;
    }

    private void configure(String databaseType) {
        DatabaseConfig config = getDatabaseConfig();
        hikariConfig.setJdbcUrl(
                DbUtils.getDriverUrl(databaseType,
                        config.getHost(),
                        config.getPort(),
                        config.getDatabaseName(),
                        config.getParams()));
        hikariConfig.addDataSourceProperty("user", config.getUser());
        hikariConfig.addDataSourceProperty("password", config.getPassword());
    }

    @Override
    public DatabaseConnection getConnection() {
        return this.connection;
    }
}
