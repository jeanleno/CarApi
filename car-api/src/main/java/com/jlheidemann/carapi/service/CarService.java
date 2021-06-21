package com.jlheidemann.carapi.service;

import com.jlheidemann.carapi.dto.CarConsumptionDto;
import com.jlheidemann.carapi.dto.CarConsumptionValuesDto;
import com.jlheidemann.carapi.dto.CarDto;
import com.jlheidemann.carapi.entity.Car;
import com.jlheidemann.carapi.repository.CarRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean.heidemann
 */
@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<CarDto> getAllCars() {
        List<CarDto> cars = new ArrayList<>();
        carRepository.findAll().forEach(c -> cars.add(CarDto.fromCar(c)));
        return cars;
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public CarDto getCar(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            return CarDto.fromCar(car);
        }
        return null;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void saveCar(CarDto car) {
        if (car == null) {
            throw new IllegalArgumentException("Car is null");
        }
        carRepository.save(car.toCar());
        
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
    
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<CarConsumptionDto> getRankedCarConsumption(CarConsumptionValuesDto values) {
        if (values == null) {
            throw new IllegalArgumentException("CarConsumptionValuesDto is null");
        }
        
        List<CarDto> cars = getAllCars();
        List<CarConsumptionDto> carConsumptionList = new ArrayList<>();
        if (cars != null && !cars.isEmpty()) {
            for (CarDto car : cars) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(car.getManufacturingDate());
                CarConsumptionDto carConsumption = new CarConsumptionDto(car.getName(), car.getBrand(), car.getModel(), cal.get(Calendar.YEAR));
                
                BigDecimal consumedFuel = (values.getCityDistance().divide(car.getCityAverageConsumption(), 2, RoundingMode.HALF_EVEN)).add((values.getHightwayDistance().divide(car.getHighwayAverageConsumption(), 2, RoundingMode.HALF_EVEN)));
                BigDecimal spentAmout = consumedFuel.multiply(values.getGasolinePrice());
                
                carConsumption.setConsumedFuel(consumedFuel);
                carConsumption.setSpentAmount(spentAmout);
                carConsumptionList.add(carConsumption);
            }
        }
        
        carConsumptionList.sort(Comparator.comparing(CarConsumptionDto::getSpentAmount)/*.reversed()*/);
        return carConsumptionList;
    }
}
