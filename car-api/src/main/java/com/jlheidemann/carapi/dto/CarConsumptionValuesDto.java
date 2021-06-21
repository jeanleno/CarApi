package com.jlheidemann.carapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author jean.heidemann
 */
public class CarConsumptionValuesDto implements Serializable {
    private BigDecimal gasolinePrice;
    private BigDecimal cityDistance;
    private BigDecimal hightwayDistance;

    public CarConsumptionValuesDto() {
    }

    public CarConsumptionValuesDto(BigDecimal gasolinePrice, BigDecimal cityDistance, BigDecimal hightwayDistance) {
        this.gasolinePrice = gasolinePrice;
        this.cityDistance = cityDistance;
        this.hightwayDistance = hightwayDistance;
    }

    public BigDecimal getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(BigDecimal gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    public BigDecimal getCityDistance() {
        return cityDistance;
    }

    public void setCityDistance(BigDecimal cityDistance) {
        this.cityDistance = cityDistance;
    }

    public BigDecimal getHightwayDistance() {
        return hightwayDistance;
    }

    public void setHightwayDistance(BigDecimal hightwayDistance) {
        this.hightwayDistance = hightwayDistance;
    }
}
