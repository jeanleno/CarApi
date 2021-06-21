package com.jlheidemann.carapi.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlheidemann.carapi.dto.CarConsumptionDto;
import com.jlheidemann.carapi.dto.CarConsumptionValuesDto;
import com.jlheidemann.carapi.dto.CarDto;
import com.jlheidemann.carapi.entity.Car;
import com.jlheidemann.carapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author jean.heidemann
 */
@WebMvcTest
public class CarControllerTest {
    private static final ObjectMapper om = new ObjectMapper();
     
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;
    
    @BeforeEach
    public void init() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        
        CarDto car = new CarDto(99L, "C4", "Citroen", "Picasso", Date.valueOf("2021-06-21"), new BigDecimal(7), new BigDecimal(11));
        when(carService.getCar(99L)).thenReturn(car);
    }
    
    @Test
    public void findCarById_OK() throws Exception {

        mockMvc.perform(get("/cars/99"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(99)))
                .andExpect(jsonPath("$.name", is("C4")))
                .andExpect(jsonPath("$.brand", is("Citroen")))
                .andExpect(jsonPath("$.model", is("Picasso")))
                .andExpect(jsonPath("$.manufacturingDate", is("2021-06-21")))
                .andExpect(jsonPath("$.cityAverageConsumption", is(7)))
                .andExpect(jsonPath("$.highwayAverageConsumption", is(11)));

        verify(carService, times(1)).getCar(99L);
    }
    
    @Test
    public void findAllCars_OK() throws Exception {

        List<CarDto> cars = Arrays.asList(
                new CarDto(100L, "C4", "Citroen", "Picasso", Date.valueOf("2021-06-21"), new BigDecimal(7), new BigDecimal(11)),
                new CarDto(101L, "C3", "Citroen", "GLX", Date.valueOf("2021-06-21"), new BigDecimal(9), new BigDecimal(13)));

        when(carService.getAllCars()).thenReturn(cars);

        mockMvc.perform(get("/cars"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(100)))
                .andExpect(jsonPath("$[0].name", is("C4")))
                .andExpect(jsonPath("$[0].brand", is("Citroen")))
                .andExpect(jsonPath("$[0].model", is("Picasso")))
                .andExpect(jsonPath("$[0].manufacturingDate", is("2021-06-21")))
                .andExpect(jsonPath("$[0].cityAverageConsumption", is(7)))
                .andExpect(jsonPath("$[0].highwayAverageConsumption", is(11)))
                .andExpect(jsonPath("$[1].id", is(101)))
                .andExpect(jsonPath("$[1].name", is("C3")))
                .andExpect(jsonPath("$[1].brand", is("Citroen")))
                .andExpect(jsonPath("$[1].model", is("GLX")))
                .andExpect(jsonPath("$[1].manufacturingDate", is("2021-06-21")))
                .andExpect(jsonPath("$[1].cityAverageConsumption", is(9)))
                .andExpect(jsonPath("$[1].highwayAverageConsumption", is(13)));

        verify(carService, times(1)).getAllCars();
    }

    @Test
    public void findCarById_NotFound_404() throws Exception {
        mockMvc.perform(get("/cars/999")).andExpect(status().isNotFound());
    }

    @Test
    public void saveCarOK() throws Exception {

        CarDto newCar = new CarDto(100L, "C4", "Citroen", "Picasso", Date.valueOf("2021-06-21"), new BigDecimal(7), new BigDecimal(11));
       
        mockMvc.perform(post("/cars")
                .content(om.writeValueAsString(newCar))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(carService, times(1)).saveCar(any(CarDto.class));

    }

    @Test
    public void updateCar_OK() throws Exception {

        CarDto updateCar = new CarDto(100L, "C4", "Citroen", "Picasso", Date.valueOf("2021-06-21"), new BigDecimal(7), new BigDecimal(11));
        
        mockMvc.perform(put("/cars")
                .content(om.writeValueAsString(updateCar))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(carService, times(1)).saveCar(any(CarDto.class));
    }

    @Test
    public void deleteCar_OK() throws Exception {
        doNothing().when(carService).deleteCar(1L);

        mockMvc.perform(delete("/cars/1")).andExpect(status().isOk());

        verify(carService, times(1)).deleteCar(1L);
    }
    
    @Test
    public void getRankedCarConsumption_OK() throws Exception {
        CarConsumptionValuesDto values = new CarConsumptionValuesDto(new BigDecimal(4), new BigDecimal(10), new BigDecimal(10));
        
        List<CarConsumptionDto> cars = Arrays.asList(new CarConsumptionDto("Etios", "Toyota", "Platinum", 2019, new BigDecimal(8), new BigDecimal(32)),
            new CarConsumptionDto("C3", "Citroen", "GLX", 2019, new BigDecimal(9), new BigDecimal(36)),
            new CarConsumptionDto("C4", "Citroen", "Picasso", 2019, new BigDecimal(10), new BigDecimal(40)));

        when(carService.getRankedCarConsumption(any(CarConsumptionValuesDto.class))).thenReturn(cars);

        mockMvc.perform(post("/cars/rank")
                .content(om.writeValueAsString(values))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Etios")))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[0].model", is("Platinum")))
                .andExpect(jsonPath("$[0].year", is(2019)))
                .andExpect(jsonPath("$[0].consumedFuel", is(8)))
                .andExpect(jsonPath("$[0].spentAmount", is(32)))
                .andExpect(jsonPath("$[1].name", is("C3")))
                .andExpect(jsonPath("$[1].brand", is("Citroen")))
                .andExpect(jsonPath("$[1].model", is("GLX")))
                .andExpect(jsonPath("$[1].year", is(2019)))
                .andExpect(jsonPath("$[1].consumedFuel", is(9)))
                .andExpect(jsonPath("$[1].spentAmount", is(36)))
                .andExpect(jsonPath("$[2].name", is("C4")))
                .andExpect(jsonPath("$[2].brand", is("Citroen")))
                .andExpect(jsonPath("$[2].model", is("Picasso")))
                .andExpect(jsonPath("$[2].year", is(2019)))
                .andExpect(jsonPath("$[2].consumedFuel", is(10)))
                .andExpect(jsonPath("$[2].spentAmount", is(40)));

        verify(carService, times(1)).getRankedCarConsumption(any(CarConsumptionValuesDto.class));
    }
}
