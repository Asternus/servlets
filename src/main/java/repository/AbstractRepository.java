package repository;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class AbstractRepository {
    @SneakyThrows
    public static Connection createCon() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "root";
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void restartBd() {
        try {
            String clear = "TRUNCATE TABLE factory CASCADE";
            String alter = "ALTER SEQUENCE factory_factory_id_seq RESTART WITH 1";
            String clear1 = "TRUNCATE TABLE device CASCADE";
            String alter1 = "ALTER SEQUENCE device_device_id_seq RESTART WITH 1";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                statement.addBatch(clear);
                statement.addBatch(alter);
                statement.addBatch(clear1);
                statement.addBatch(alter1);
                statement.executeBatch();
                statement.clearBatch();
                System.out.println("restartBd");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }
}