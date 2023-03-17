package Entity;

import lombok.Data;

@Data
public class Factory {
    private int factoryId;
    private String name;
    private String country;

    public Factory() {
    }

    public Factory(int factoryId, String name, String country) {
        this.factoryId = factoryId;
        this.name = name;
        this.country = country;
    }

}