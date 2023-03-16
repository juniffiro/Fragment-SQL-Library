package ua.juniffiro.sql.lib.utils;

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
public class DbUtils {

    /**
     * Get url to initialize database driver.
     *
     * @param databaseType
     *        MySQL or another type of database
     * @param params
     *        Database options
     *
     * @return Driver url.
     */
    public static String getDriverUrl(String databaseType, String host, int port, String databaseName,
                                      SQLParam... params) {
        StringBuilder s = new StringBuilder("jdbc:" + databaseType + "://" + host + ":" + port + "/"
                + databaseName);

        if (params.length > 0) {
            s.append("?");
            for (SQLParam param : params) {
                s.append(param.getName())
                        .append("=")
                        .append(param.getValue().toString())
                        .append("&");
            }
        }
        return s.toString();
    }

    public static SQLParam[] getStandardParams() {
        return new SQLParam[]{
                new SQLParam("autoReconnect", true),
                new SQLParam("useSSL", true),
                new SQLParam("serverTimezone", "UTC")
        };
    }

    /**
     * Close database connection.
     * <p>
     * Ignore SQL exceptions on close.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    /**
     * Close database connection
     * and prepared statement.
     * <p>
     * Ignore SQL exceptions on close.
     *
     * @param ps
     *        PreparedStatement
     */
    public static void close(Connection connection, PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ignored) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    /**
     * Close database connection
     * with all resources.
     * <p>
     * Ignore SQL exceptions on close.
     *
     * @param rs
     *        ResultSet
     * @param ps
     *        PreparedStatement
     */
    public static void close(Connection connection, ResultSet rs, PreparedStatement ps) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ignored) {
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ignored) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }
}
