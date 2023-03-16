package ua.juniffiro.sql.lib.test;

import ua.juniffiro.sql.lib.ModuleSQL;
import ua.juniffiro.sql.lib.database.Database;
import ua.juniffiro.sql.lib.database.DatabaseConfig;
import ua.juniffiro.sql.lib.database.hikari.Hikari;
import ua.juniffiro.sql.lib.database.hikari.HikariConfig2;
import ua.juniffiro.sql.lib.database.mysql.classic.Mysql;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class Data {

    public static DatabaseConfig DB_CONFIG = new DatabaseConfig.Builder()
            .setHost("localhost")
            .setPort(3306)
            .setUser("root")
            .setPassword("root")
            .setDatabaseName("test_db")
            .build();

//    public static HikariConfig HIKARI = new HikariConfig();
//
//    static {
//        HIKARI.addDataSourceProperty("cachePrepStmts", "true");
//        HIKARI.addDataSourceProperty("prepStmtCacheSize", "250");
//        HIKARI.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//    }

    //public static Database MYSQL_DATABASE = new Mysql(DB_CONFIG);

    public static Database DATABASE = new Hikari(DB_CONFIG, new HikariConfig2()
            .addProperty("cachePrepStmts", "true")
            .addProperty("prepStmtCacheSize", "250")
            .addProperty("prepStmtCacheSqlLimit", "2048"));

    public static ModuleSQL SQL = new ModuleSQL(DATABASE);
}
