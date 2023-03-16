package ua.juniffiro.sql.lib.database;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface DatabaseCloseable {

    /**
     * This method is implemented and serves to close additional
     * resources if necessary. This can be a connection
     * pool or something else.
     */
    void close();
}
