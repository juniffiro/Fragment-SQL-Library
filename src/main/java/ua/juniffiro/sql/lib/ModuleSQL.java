package ua.juniffiro.sql.lib;

import ua.juniffiro.sql.lib.database.Database;
import ua.juniffiro.sql.lib.database.DatabaseCloseable;
import ua.juniffiro.sql.lib.database.operations.*;

import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ModuleSQL {

    private final Database database; // Database instance
    private final SqlOperations sqlOperations;
    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

    // To summarize everything into a module
    public ModuleSQL(Database database) {
        this.database = database;
        // Initializing request manager
        this.sqlOperations = new ClassicSqlOperations(database.getConnection());
    }

    public Database getDatabase() {
        return database;
    }

    /**
     * See {@link SqlOperations#execute(String)}
     */
    public int sync(String sql) {
        return sqlOperations.execute(sql);
    }

    /**
     * Execute SQL query asynchronously.
     * Expect the result of operation
     * ({@link java.lang.Integer}) in the callback.
     *
     * @param sql
     *        SQL query
     *
     * @return Operation result in
     *         {@link java.util.concurrent.Future}.
     */
    public Future<Integer> async(String sql) {
        return executorService.submit(() -> sync(sql));
    }

    /**
     * See {@link SqlOperations#executeQuery(PreparedStatementCreator, Callback)}
     */
    public void syncQuery(PreparedStatementCreator psCreator, Callback<ResultSet> callback) {
        sqlOperations.executeQuery(psCreator, callback);
    }

    /**
     * See {@link SqlOperations#executeUpdate(PreparedStatementCreator, Callback)}
     */
    public void syncUpdate(PreparedStatementCreator psCreator, Callback<Integer> callback) {
        sqlOperations.executeUpdate(psCreator, callback);
    }

    /**
     * Execute database query asynchronously.
     * Waiting for {@link java.sql.ResultSet}
     * in the callback.
     *
     * @param psCreator
     *        Statement creator
     * @param callback
     *        Operation result
     */
    public void asyncQuery(PreparedStatementCreator psCreator, Callback<ResultSet> callback) {
        executorService.execute(() -> syncQuery(psCreator, callback));
    }

    /**
     * Execute database query asynchronously.
     * Waiting for {@link java.lang.Integer}
     * in the callback.
     *
     * @param psCreator
     *        Statement creator
     * @param callback
     *        Operation result
     */
    public void asyncUpdate(PreparedStatementCreator psCreator, Callback<Integer> callback) {
        executorService.execute(() -> syncUpdate(psCreator, callback));
    }

    /**
     * See {@link SqlOperations#buildTransaction()}
     */
    public Transaction createTransaction() {
        return sqlOperations.buildTransaction();
    }

    /**
     * Waiting 10 seconds for all requests
     * to complete and shutdown.
     */
    public void shutdown() {
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        if (database.getConnection() instanceof DatabaseCloseable) {
            ((DatabaseCloseable) database.getConnection()).close();
        }
    }
}
