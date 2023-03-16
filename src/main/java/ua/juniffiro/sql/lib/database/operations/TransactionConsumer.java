package ua.juniffiro.sql.lib.database.operations;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 03/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface TransactionConsumer {

    void accept(Connection connection) throws SQLException;

    void onError(Connection connection, Throwable throwable) throws SQLException;
}
