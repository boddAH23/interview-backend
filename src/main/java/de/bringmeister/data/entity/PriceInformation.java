package de.bringmeister.data.entity;

public class PriceInformation {

    private String id;

    private Unit unit;

    private Price price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getUnitDescription() {
        return unit.getName();
    }
}
