package com.jlheidemann.carapi.controller;

import com.jlheidemann.carapi.dto.CarConsumptionDto;
import com.jlheidemann.carapi.dto.CarConsumptionValuesDto;
import com.jlheidemann.carapi.dto.CarDto;
import com.jlheidemann.carapi.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jean.heidemann
 */
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @Operation(summary = "Get a all cars")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all cars",
            content = {
                @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CarDto.class))
                    )})
        })
    @GetMapping("/cars")
    public ResponseEntity<List> getAllCars() {
        List cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Operation(summary = "Get a car by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the car",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CarDto.class))})
        ,
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
            content = @Content)
        , 
      @ApiResponse(responseCode = "404", description = "Car not found",
            content = @Content)})
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        if (id == null || id < 1) {
            return new ResponseEntity("Invalid id supplied", HttpStatus.BAD_REQUEST);
        }
        CarDto car = carService.getCar(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity("Car not found", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves a car")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CarDto.class)))
    @PostMapping("/cars")
    public ResponseEntity addCar(@RequestBody CarDto car) {
        carService.saveCar(car);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a car")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CarDto.class)))
    @PutMapping("/cars")
    public ResponseEntity updateCar(@RequestBody CarDto car) {
        carService.saveCar(car);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Delete a car by its id")
    @DeleteMapping("/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Get ranked cars")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CarConsumptionValuesDto.class)))
    @PostMapping("/cars/rank")
    public ResponseEntity<List<CarConsumptionDto>> getRankedCarConsumption(@RequestBody CarConsumptionValuesDto values) {
        List<CarConsumptionDto> carConsumptionList = carService.getRankedCarConsumption(values);
        return new ResponseEntity(carConsumptionList, HttpStatus.OK);
    }
}
