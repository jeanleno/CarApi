package com.jlheidemann.carapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author jean.heidemann
 */
public class CarConsumptionDto implements Serializable {

    private String name;
    private String brand;
    private String model;
    private int year;
    private BigDecimal consumedFuel;
    private BigDecimal spentAmount;

    public CarConsumptionDto() {
    }

    public CarConsumptionDto(String name, String brand, String model, int year) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public CarConsumptionDto(String name, String brand, String model, int year, BigDecimal consumedFuel, BigDecimal spentAmount) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.consumedFuel = consumedFuel;
        this.spentAmount = spentAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getConsumedFuel() {
        return consumedFuel;
    }

    public void setConsumedFuel(BigDecimal consumedFuel) {
        this.consumedFuel = consumedFuel;
    }

    public BigDecimal getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }

}
