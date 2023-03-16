package ua.juniffiro.sql.lib.database.mysql.classic;

import ua.juniffiro.sql.lib.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ClassicConnection implements DatabaseConnection {

    private final String url;
    private final String user;
    private final String password;

    public ClassicConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Get connection to the database using
     * {@link java.sql.DriverManager}.
     *
     * @return {@link java.sql.Connection}
     *
     * @throws SQLException
     *        In the case of an error with the database.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
