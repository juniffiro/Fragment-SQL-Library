package ua.juniffiro.sql.lib.database.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.juniffiro.sql.lib.database.DatabaseCloseable;
import ua.juniffiro.sql.lib.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class HikariPoolConnection implements DatabaseConnection, DatabaseCloseable {

    private final HikariDataSource ds;

    public HikariPoolConnection(HikariConfig config) {
        this.ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public void close() {
        if (!ds.isClosed()) {
            ds.close();
        }
    }
}
