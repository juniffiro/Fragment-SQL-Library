package ua.juniffiro.sql.lib.database;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public abstract class Database {

    /*
    Abstract database class to implement the necessary databases.
     */

    private final DatabaseConfig databaseConfig;

    public Database(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    /**
     * Connection to the database of a certain type.
     */
    public abstract DatabaseConnection getConnection();

    /**
     * Database Configuration.
     */
    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }
}
