package com.jlheidemann.carapi.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jean.heidemann
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;
    @Column(name = "model", nullable = false, length = 100)
    private String model;
    @Column(name = "manufacturing_date", nullable = false)
    private Date manufacturingDate;
    @Column(name = "city_average_consumption", nullable = false)
    private BigDecimal cityAverageConsumption;
    @Column(name = "highway_average_consumption", nullable = false)
    private BigDecimal highwayAverageConsumption;

    public Car() {
    }

    public Car(String name, String brand, String model, Date manufacturingDate, BigDecimal cityAverageConsumption, BigDecimal highwayAverageConsumption) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.cityAverageConsumption = cityAverageConsumption;
        this.highwayAverageConsumption = highwayAverageConsumption;
    }

    public Car(Long id, String name, String brand, String model, Date manufacturingDate, BigDecimal cityAverageConsumption, BigDecimal highwayAverageConsumption) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.cityAverageConsumption = cityAverageConsumption;
        this.highwayAverageConsumption = highwayAverageConsumption;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public BigDecimal getCityAverageConsumption() {
        return cityAverageConsumption;
    }

    public void setCityAverageConsumption(BigDecimal cityAverageConsumption) {
        this.cityAverageConsumption = cityAverageConsumption;
    }

    public BigDecimal getHighwayAverageConsumption() {
        return highwayAverageConsumption;
    }

    public void setHighwayAverageConsumption(BigDecimal highwayAverageConsumption) {
        this.highwayAverageConsumption = highwayAverageConsumption;
    }

}
