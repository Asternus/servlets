package repository;

import Entity.Device;
import Entity.Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactoryRepository extends AbstractRepository {

    public static void addLinesFactory() {
        try {
            String sql = "INSERT INTO factory (factory_id, name, country) VALUES (1, 'Phone', 'China'), (2, 'Television', 'USA'), (3, 'Laptop', 'Canada'), (4, 'Robots', 'Japan')";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Factory: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void getFactoryByID(Factory factory, int id) {
        try {
            String sql = "SELECT * FROM factory WHERE factory_id = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                factory.setFactoryId(resultSet.getInt("factory_id"));
                factory.setName(resultSet.getString("name"));
                factory.setCountry(resultSet.getString("country"));
                System.out.println(factory);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public static void getDeviceByFactory(int a) {
        try {
            String sql = "SELECT device.device_id as device_id, device.type as device_type, device.name as device_name, device.price as device_price, device.date as device_date, device.description as device_description,  device.availability as device_availability, device.factory_id as device_factory_id,factory.factory_id as factory_factory_id, factory.name as factory_name, factory.country as factory_country FROM device INNER JOIN factory ON factory.factory_id = device.factory_id WHERE device.factory_id = ?";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, a);
                ResultSet rs = preparedStatement.executeQuery();
                List<Device> deviceList = new ArrayList<>();
                Map<Factory, List<Device>> factoryDeviceMap = new HashMap<>();
                while (rs.next()) {
                    deviceList.add(new Device(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getString(6), rs.getBoolean(7), rs.getInt(8)));
                    factoryDeviceMap.put(new Factory(rs.getInt(9), rs.getString(10), rs.getString(11)), deviceList);
                }
                System.out.println(factoryDeviceMap);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

}