package ua.juniffiro.sql.lib.database.operations;

import ua.juniffiro.sql.lib.database.DatabaseConnection;
import ua.juniffiro.sql.lib.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ClassicSqlOperations implements SqlOperations {

    /*
    Implementation of database operations.
     */

    private final DatabaseConnection connection;

    public ClassicSqlOperations(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public Transaction buildTransaction() {
        try {
            return new Transaction(connection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int execute(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.close(conn, ps);
        }
        return result;
    }

    @Override
    public void executeQuery(PreparedStatementCreator psCreator, Callback<ResultSet> callback) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connection.getConnection();
            ps = psCreator.createPreparedStatement(conn);
            rs = ps.executeQuery();
            if (callback != null)
                callback.onSuccess(rs);
        } catch (SQLException ex) {
            if (callback != null)
                callback.onFail(ex);
        } finally {
            DbUtils.close(conn, rs, ps);
        }
    }

    @Override
    public void executeUpdate(PreparedStatementCreator psCreator, Callback<Integer> callback) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result;
        try {
            conn = connection.getConnection();
            ps = psCreator.createPreparedStatement(conn);
            result = ps.executeUpdate();
            if (callback != null)
                callback.onSuccess(result);
        } catch (SQLException ex) {
            if (callback != null)
                callback.onFail(ex);
        } finally {
            DbUtils.close(conn, ps);
        }
    }

    @Override
    public void execute(PreparedStatementCreator psCreator, Callback<Boolean> callback) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result;
        try {
            conn = connection.getConnection();
            ps = psCreator.createPreparedStatement(conn);
            result = ps.execute();
            if (callback != null)
                callback.onSuccess(result);
        } catch (SQLException ex) {
            if (callback != null)
                callback.onFail(ex);
        } finally {
            DbUtils.close(conn, ps);
        }
    }
}
