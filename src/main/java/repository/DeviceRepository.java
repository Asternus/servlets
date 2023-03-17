package repository;

import Entity.Device;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class DeviceRepository extends AbstractRepository {
    public static void addLinesDevice() {
        try {
            String sql = "INSERT INTO device (device_id, type, name, price, date, description, availability, factory_id) VALUES " +
                    "(1,'Smartphone', 'X-100', 200.0, '2022-01-01', 'top smartphone', true, 1), " +
                    "(2, 'Mobile', '3310', 100.0, '2018-02-11', 'Nokia', true, 1), " +
                    "(3, 'Phone', 'cell', 50.0, '2000-02-11', 'Siemens', true, 1), " +
                    "(4, 'SmartTV', 'I800', 1500.0, '2021-08-08', 'Panasonic', true, 2), " +
                    "(5, 'TVBox', 'E100', 600.0, '2021-02-02', 'Sony', true, 2), " +
                    "(6, 'TV', 'B777', 550.0, '2018-02-03', 'Samsung', true, 2), " +
                    "(7, 'NetBook', 'VEST22', 4000.0, '2021-05-04', 'Dell', true, 3), " +
                    "(8, 'ProBook', 'IST51', 12000.0, '2020-04-11', 'Asus', true, 3), " +
                    "(9, 'CleanRobot', 'T1000', 9500.0, '2021-11-11', 'ITRobots', true, 4), " +
                    "(10, 'WorkRobot', 'Terminator', 12000.0, '2021-08-08', 'ITRobots', true, 4)";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Device: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void addLToDb(final Device device) {
        try {
            String sql = "INSERT INTO device (device_id, type, name, price, date, description, availability, factory_id) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, device.getDeviceId());
                preparedStatement.setString(2, device.getType());
                preparedStatement.setString(3, device.getName());
                preparedStatement.setDouble(4, device.getPrice());
                preparedStatement.setDate(5, device.getDate());
                preparedStatement.setString(6, device.getDescription());
                preparedStatement.setBoolean(7, device.isAvailability());
                preparedStatement.setInt(8, device.getFactoryId());
                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Device: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void getDeviceById(Device device, int id) {
        try {
            String sql = "SELECT * FROM device WHERE device_id = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                device.setDeviceId(resultSet.getInt("device_id"));
                device.setType(resultSet.getString("type"));
                device.setName(resultSet.getString("name"));
                device.setPrice(resultSet.getDouble("price"));
                device.setDate(resultSet.getDate("date"));
                device.setDescription(resultSet.getString("description"));
                device.setAvailability(resultSet.getBoolean("availability"));
                device.setFactoryId(resultSet.getInt("factory_id"));
                System.out.println(device);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static Device getDeviceByName(String name) {
        Device device = new Device();
        try {
            String sql = "SELECT * FROM device WHERE name = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                device.setDeviceId(resultSet.getInt("device_id"));
                device.setType(resultSet.getString("type"));
                device.setName(resultSet.getString("name"));
                device.setPrice(resultSet.getDouble("price"));
                device.setDate(resultSet.getDate("date"));
                device.setDescription(resultSet.getString("description"));
                device.setAvailability(resultSet.getBoolean("availability"));
                device.setFactoryId(resultSet.getInt("factory_id"));
                return device;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        return null;
    }

    public static Device getDeviceById(int id) {
        try {
            final Device device = new Device();
            String sql = "SELECT * FROM device WHERE device_id = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                device.setDeviceId(resultSet.getInt("device_id"));
                device.setType(resultSet.getString("type"));
                device.setName(resultSet.getString("name"));
                device.setPrice(resultSet.getDouble("price"));
                device.setDate(resultSet.getDate("date"));
                device.setDescription(resultSet.getString("description"));
                device.setAvailability(resultSet.getBoolean("availability"));
                device.setFactoryId(resultSet.getInt("factory_id"));
                return device;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        return null;
    }

    public static void updateDevice(int id, Device device) {
        try {
            String sql = "UPDATE device SET type=?, name =?, price = ?, date = ?, description = ?, availability = ? WHERE device_id = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, device.getType());
                preparedStatement.setString(2, device.getName());
                preparedStatement.setDouble(3, device.getPrice());
                preparedStatement.setDate(4, device.getDate());
                preparedStatement.setString(5, device.getDescription());
                preparedStatement.setBoolean(6, device.isAvailability());
                preparedStatement.setInt(7, id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void stat(int id) {
        try {
            String userId = "1 or 1 = '1'";
            String sql = "SELECT * FROM device where device_id = " + userId;
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);
                int i = 0;
                while (resultSet.next()) {
                    System.out.println(i + " type " + resultSet.getString("type"));
                    System.out.println(i + " name "  + resultSet.getString("name"));
                    System.out.println(i + " price " + resultSet.getDouble("price"));
                    i ++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void getCallableStatement() {
        try {
            try (Connection conn = createCon();
                 CallableStatement callableStatement = conn.prepareCall("{call get_geom_difference()}")) {
                final ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }

            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void getCallableStatementWithParams(final int id) {
        try {
            try (Connection conn = createCon();
                 CallableStatement callableStatement = conn.prepareCall("{call get_difference(?)}")) {
                callableStatement.setInt(1, id);
                final ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void savePoint(int id, Device device) {
        Connection conn = createCon();
        try {
            String sql = "UPDATE device SET type=?, name =?, price = ?, date = ?, description = ?, availability = ? WHERE device_id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);
                preparedStatement.addBatch("SELECT * FROM device WHERE device_id =");
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();

                conn.commit();

                preparedStatement.setString(1, device.getType());
                preparedStatement.setString(2, device.getName());
                preparedStatement.setDouble(3, device.getPrice());
                preparedStatement.setDate(4, device.getDate());
                final Savepoint savepoint = conn.setSavepoint();
                preparedStatement.setString(5, device.getDescription());
                preparedStatement.setBoolean(6, device.isAvailability());

                conn.rollback(savepoint);

                preparedStatement.setInt(7, id);
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void transaction(int id, Device device) {
        Connection conn = createCon();
        try {
            String sql = "UPDATE device SET type=?, name =?, price = ?, date = ?, description = ?, availability = ? WHERE device_id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);
                preparedStatement.setString(1, device.getType());
                preparedStatement.setString(2, device.getName());
                preparedStatement.setDouble(3, device.getPrice());
                preparedStatement.setDate(4, device.getDate());
                preparedStatement.setString(5, device.getDescription());
                preparedStatement.setBoolean(6, device.isAvailability());
                preparedStatement.setInt(7, id);
                preparedStatement.executeUpdate();
                conn.commit();
            }
        } catch (Exception ex) {
            if (conn != null) {
                System.out.println("Transaction is being rolled back");
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void delDevice(int id) {
        try {
            String sql = "DELETE FROM device WHERE device_id = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                int row = preparedStatement.executeUpdate();
                System.out.println("delete device by id " + id + " = " + row);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void getSum() {
        try {
            String sql = "SELECT factory_id, SUM(price), COUNT(factory_id) FROM device GROUP BY factory_id ORDER BY factory_id";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    System.out.println("ID: " + rs.getString(1));
                    System.out.println("Sum device: " + rs.getString(2));
                    System.out.println("Count: " + rs.getString(3) + "\n");
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

}