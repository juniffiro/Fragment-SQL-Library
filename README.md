# 💾Fragment: Java SQL Library

**Fragment** is a Java library for working with SQL-type databases.
<br/>

*Features* ✅

* Easy to use
* Simple SQL queries
* SQL queries with callback
* Transactions
* Uses prepared statements
* Easily works with database pools

## Example

> To use it, it is recommended to create a separate class and initialize 
> the database with a static field.

First, configure the database configuration.

```java
public static DatabaseConfig DB_CONFIG = new DatabaseConfig.Builder()
        .setHost("localhost")
        .setPort(3306)
        .setUser("root")
        .setPassword("")
        .setDatabaseName("test_db")
        .build();
```
By default:

| Key            | Value               |
|----------------|---------------------|
| databaseType   | mysql               |
| host           | 127.0.0.1 (localhost) |
| port           | 3306                |
| user           | root                |
| password       | (empty)             |
| databaseName   | test_db             |

| Param          | Value |
|----------------|-------|
| autoReconnect  | true  |
| useSSL         | true  |
| serverTimeZone | UTC   |

Example with advanced setup
```java
public static DatabaseConfig DB_CONFIG = new DatabaseConfig.Builder()
        .setDatabaseType("mysql")
        .setHost("database.test.host")
        .setPort(3307)
        .setUser("admin")
        .setPassword("adminTestPass")
        .setDatabaseName("videos")
        .setParams(
                new SQLParam("serverTimeZone", "UTC"),
                new SQLParam("autoReconnect", true))
        .build();
```

<br/>

Next, initialize the database.

## MySQL

```java
public static Database MYSQL_DATABASE = new Mysql(DB_CONFIG);
```

## HikariCP with MySQL

```java
public static Database DATABASE = new Hikari(DB_CONFIG, new HikariConfig2()
        .addProperty("cachePrepStmts","true")
        .addProperty("prepStmtCacheSize","250")
        .addProperty("prepStmtCacheSqlLimit","2048"));
```

Create new instance of the SQL module

```java
public static ModuleSQL SQL = new ModuleSQL(DATABASE);
```

## Test it!

We can import the static SQL module
<br/>
In my case, it's:
```java
import static ua.juniffiro.sql.lib.test.Data.SQL;
```

Then we can send the SQL query

```java
SQL.async("CREATE TABLE IF NOT EXISTS `video` (id int(11), name varchar(36))");
```

We can also use a callback SQL query

```java
SQL.asyncQuery(con -> con.prepareStatement("SELECT * FROM `videos`"), new Callback<ResultSet>() {
    @Override 
    public void onSuccess(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("title"));
        }
    }
    
    @Override 
    public void onFail(Throwable throwable) {
        throwable.printStackTrace();
    }
});
```

At the end of the program you need to call the shutdown method.

```java
SQL.shutdown();
```
<br>

> Full example you can find in [example package]().

## Open source

Fragment is an open source project distributed under the Apache License 2.0 <br>

## Getting started

1. Download the latest build from releases
2. Read the FAQ and examples
3. Enjoy!

## Status

Fragment is in beta release. <br>