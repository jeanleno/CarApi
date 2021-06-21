package com.jlheidemann.carapi.dto;

import com.jlheidemann.carapi.entity.Car;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author jeanl
 */
public class CarDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String brand;
    private String model;
    private Date manufacturingDate;
    private BigDecimal cityAverageConsumption;
    private BigDecimal highwayAverageConsumption;
    
    public CarDto() {
    }

    public CarDto(String name, String brand, String model, Date manufacturingDate, BigDecimal cityAverageConsumption, BigDecimal highwayAverageConsumption) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.cityAverageConsumption = cityAverageConsumption;
        this.highwayAverageConsumption = highwayAverageConsumption;
    }

    public CarDto(Long id, String name, String brand, String model, Date manufacturingDate, BigDecimal cityAverageConsumption, BigDecimal highwayAverageConsumption) {
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
    
    public Car toCar() {
        Car car = new Car();
        car.setId(this.getId());
        car.setName(this.getName());
        car.setBrand(this.getBrand());
        car.setModel(this.getModel());
        car.setManufacturingDate(this.getManufacturingDate());
        car.setCityAverageConsumption(this.getCityAverageConsumption());
        car.setHighwayAverageConsumption(this.getHighwayAverageConsumption());
        
        return car;
    }
    
    public static CarDto fromCar(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setName(car.getName());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setManufacturingDate(car.getManufacturingDate());
        dto.setCityAverageConsumption(car.getCityAverageConsumption());
        dto.setHighwayAverageConsumption(car.getHighwayAverageConsumption());
        
        return dto;
    }
    
    
    
}
