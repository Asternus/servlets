package Entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Device {
    private int deviceId;
    private String type;
    private String name;
    private double price;
    private Date date;
    private String description;
    private boolean availability;
    private int factoryId;

    public Device() {
    }

    public Device(int deviceId, String type, String name, double price, Date date, String description, boolean availability, int factoryId) {
        this.deviceId = deviceId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.availability = availability;
        this.factoryId = factoryId;
    }
}