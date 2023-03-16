package ua.juniffiro.sql.lib.database.operations;

import java.sql.ResultSet;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface SqlOperations {

    /**
     * Create new transaction for
     * operations with the database.
     */
    Transaction buildTransaction();

    /**
     * Execute SQL query synchronously.
     * Expect the result of operation
     * ({@link java.lang.Integer}) in the callback.
     *
     * @param sql
     *        SQL query
     *
     * @return Operation result.
     */
    int execute(String sql);

    /**
     * Execute database query synchronously.
     * Waiting for {@link java.sql.ResultSet}
     * in the callback.
     *
     * @param psCreator
     *        Statement creator
     * @param callback
     *        Operation result
     */
    void executeQuery(PreparedStatementCreator psCreator, Callback<ResultSet> callback);

    /**
     * Execute database query synchronously.
     * Waiting for {@link java.lang.Integer}
     * in the callback.
     *
     * @param psCreator
     *        Statement creator
     * @param callback
     *        Operation result
     */
    void executeUpdate(PreparedStatementCreator psCreator, Callback<Integer> callback);

    /**
     * Execute database query synchronously.
     * Waiting for {@link java.lang.Boolean}
     * in the callback.
     *
     * @param psCreator
     *        Statement creator
     * @param callback
     *        Operation result
     */
    void execute(PreparedStatementCreator psCreator, Callback<Boolean> callback);
}

