package ua.juniffiro.sql.lib.database.operations;

import java.sql.SQLException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface Callback<T> {

    /**
     * Executed if the request is successful.
     *
     * @param data Data. There may be 3 types
     *                   {@link java.sql.ResultSet}
     *                   {@link java.lang.Integer}
     *                   {@link java.lang.Boolean}
     *
     * @throws SQLException
     *         Handle exception.
     */
    void onSuccess(T data) throws SQLException;

    /**
     * Called when an error occurs
     * during request processing.
     *
     * @param throwable Error.
     */
    void onFail(Throwable throwable);
}
