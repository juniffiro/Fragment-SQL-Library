package ua.juniffiro.sql.lib.database.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface PreparedStatementCreator {

    /**
     * Create new Prepared Statement
     *
     * @param con
     *        Database connection
     *
     * @return {@link java.sql.PreparedStatement}
     *
     * @throws SQLException
     *        Handle exception.
     */
    PreparedStatement createPreparedStatement(Connection con) throws SQLException;
}
