package ua.juniffiro.sql.lib.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface DatabaseConnection {

    /**
     * Get new database connection.
     *
     * @return {@link java.sql.Connection}
     *
     * @throws SQLException
     *         Handle an exception that occurs
     *         when performing an operation
     *         with the database.
     */
    Connection getConnection() throws SQLException;
}
