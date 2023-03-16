package ua.juniffiro.sql.lib.database;

import ua.juniffiro.sql.lib.utils.DbUtils;
import ua.juniffiro.sql.lib.utils.SQLParam;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class DatabaseConfig {

    private final String databaseType;
    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final SQLParam[] params;

    private final String databaseName;

    public DatabaseConfig(Builder builder) {
        this.databaseType = builder.databaseType;
        this.host = builder.host;
        this.databaseName = builder.databaseName;
        this.port = builder.port;
        this.user = builder.user;
        this.password = builder.password;
        this.params = builder.params;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public SQLParam[] getParams() {
        return params;
    }

    public static class Builder {
        /*
        By default
         */
        private String databaseType = "mysql";
        private String host = "127.0.0.1";
        private int port = 3306;
        private String user = "root";
        private String password = "";
        private String databaseName;
        private SQLParam[] params = DbUtils.getStandardParams();

        /**
         * Specific database driver implementation.
         * Example: mysql, postresql.
         * By default - mysql.
         */
        public Builder setDatabaseType(String databaseType) {
            this.databaseType = databaseType;
            return this;
        }

        /**
         * The server where the database is running.
         *
         * @param host
         *        By default - 127.0.0.1 (localhost)
         */
        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Name of the database to connect to.
         */
        public Builder setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Set parameters for configuring the database.
         * Example: timeZone
         * @param params
         *        Parameters for configuring
         */
        public Builder setParams(SQLParam... params) {
            this.params = params;
            return this;
        }

        /**
         * Create new database instance.
         * @return @{@link ua.juniffiro.sql.lib.database.DatabaseConfig}
         */
        public DatabaseConfig build() {
            return new DatabaseConfig(this);
        }
    }
}
