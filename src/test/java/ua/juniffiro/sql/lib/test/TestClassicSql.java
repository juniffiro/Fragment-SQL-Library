package ua.juniffiro.sql.lib.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ua.juniffiro.sql.lib.database.operations.Callback;
import ua.juniffiro.sql.lib.database.operations.Transaction;
import ua.juniffiro.sql.lib.database.operations.TransactionConsumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.juniffiro.sql.lib.test.Data.SQL;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 03/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
@ExtendWith(TimingExtension.class)
public class TestClassicSql {

    @BeforeAll
    public static void startTests() {
        System.out.println("Start sql lib tests");
    }

    @BeforeEach
    public void beforeEach() {
        assertNotNull(SQL);
        assertNotNull(SQL.getDatabase());
    }

    @Test
    public void testSql() {
        SQL.async("CREATE TABLE IF NOT EXISTS `videos` (`id` int(11), `title` varchar(36))");
    }

    @Test
    public void testInsert() {
        SQL.asyncUpdate(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO `videos` VALUES (?, ?);");
            ps.setInt(1, 1);
            ps.setString(2, "Hello World");
            return ps;
        }, new Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                System.out.printf("Result %d%n", data);
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Test
    public void testUpdate() {
        SQL.asyncUpdate(con -> {
            PreparedStatement ps = con.prepareStatement("UPDATE `videos` SET `title` = ? WHERE id = ?");
            ps.setString(1, "Test video");
            ps.setInt(2, 1);
            return ps;
        }, new Callback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                System.out.printf("Result %d%n", data);
            }

            @Override
            public void onFail(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Test
    public void testQuery() {
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
    }

    @Test
    public void testTransaction() {
        Transaction transaction = SQL.createTransaction();
        transaction.statement(new TransactionConsumer() {
            @Override
            public void accept(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SELECT 1");
                ResultSet rs = ps.executeQuery();
                System.out.printf("ResultSet (SELECT 1) %s%n", rs.toString());
                rs.close();
            }

            @Override
            public void onError(Connection connection, Throwable throwable) throws SQLException {
                connection.rollback();
            }
        });
        try {
            transaction.commit();
        } catch (SQLException e) {
            System.err.println("Commit failed: " + transaction.getName());
        }
    }

    @AfterAll
    public static void finish() {
        SQL.shutdown();
    }
}
