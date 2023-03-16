package ua.juniffiro.sql.lib.database.operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.ThreadLocalRandom;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 03/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class Transaction {

    private String name;
    private final Connection conn;

    // By default
    public Transaction(Connection connection) throws SQLException {
        this("Transaction-" + ThreadLocalRandom.current().nextInt(),
                connection);
    }

    public Transaction(String name, Connection connection) throws SQLException {
        this.name = name;
        this.conn = connection;

        connection.setAutoCommit(false);
        save("Entry point");
    }

    /**
     * Set new name for transaction.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get transaction name.
     */
    public String getName() {
        return name;
    }

    /**
     * Create new savepoint.
     * @param name
     *        Name for the savepoint
     *
     * @return {@link java.sql.Savepoint}
     *
     * @throws SQLException
     *         If an error occurs in the database
     */
    public Savepoint save(String name) throws SQLException {
        return conn.setSavepoint(name);
    }

    /**
     * Create a new statement and perform actions
     * with the database in it.
     * The results are expected in the callback.
     */
    public void statement(TransactionConsumer consumer) {
        try {
            consumer.accept(conn);
        } catch (SQLException e) {
            try {
                consumer.onError(conn, e);
            } catch (SQLException ignored) {
            }
        }
    }

    /**
     * Roll back all changes to the database
     * to a certain save point.
     *
     * @throws SQLException
     *         If an error occurs in the database
     */
    public void rollback(Savepoint savepoint) throws SQLException {
        conn.rollback(savepoint);
    }

    /**
     * Send all changes to the database.
     * @throws SQLException
     *         If an error occurs in the database
     */
    public void commit() throws SQLException {
        conn.commit();
    }
}
