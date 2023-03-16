package ua.juniffiro.sql.lib.database.hikari;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 06/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class HikariConfig2 extends HikariConfig {

    /*
    Wrapper for HikariConfig
     */

    public HikariConfig2() {}

    public HikariConfig2(Properties properties) {
        super(properties);
    }

    public HikariConfig2(String propertyFileName) {
        super(propertyFileName);
    }

    public HikariConfig2 addProperty(String key, Object value) {
        addDataSourceProperty(key, value);
        return this;
    }
}
