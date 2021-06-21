package com.jlheidemann.carapi.dataloader;

import com.jlheidemann.carapi.entity.Car;
import com.jlheidemann.carapi.repository.CarRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeanl
 */
@Component
public class DataLoader implements ApplicationRunner {
    private CarRepository carRepository;

    @Autowired
    public DataLoader(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void run(ApplicationArguments args) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        
        try {
            this.carRepository.save(new Car("Civic", "HONDA", "LXL", new Date(dateFormat.parse("05/05/2011").getTime()), new BigDecimal(9.5), new BigDecimal(13)));
            this.carRepository.save(new Car("Fit", "HONDA", "LX", new Date(dateFormat.parse("05/02/2013").getTime()), new BigDecimal(12), new BigDecimal(13.5)));
            this.carRepository.save(new Car("KA", "FORD", "SE", new Date(dateFormat.parse("06/08/2016").getTime()), new BigDecimal(11.4), new BigDecimal(15)));
            this.carRepository.save(new Car("Focus", "FORD", "SEL", new Date(dateFormat.parse("10/07/2015").getTime()), new BigDecimal(8.3), new BigDecimal(11.5)));
            this.carRepository.save(new Car("Etios", "TOYOTA", "Platinum", new Date(dateFormat.parse("21/11/2015").getTime()), new BigDecimal(14), new BigDecimal(21)));
            this.carRepository.save(new Car("Corolla", "TOYOTA", "XEI", new Date(dateFormat.parse("07/02/2012").getTime()), new BigDecimal(9.5), new BigDecimal(12.5)));
            this.carRepository.save(new Car("Onix", "CHEVROLET", "JOY", new Date(dateFormat.parse("23/10/2017").getTime()), new BigDecimal(13.4), new BigDecimal(16)));
            this.carRepository.save(new Car("Cruze", "CHEVROLET", "LTZ", new Date(dateFormat.parse("12/02/2019").getTime()), new BigDecimal(11), new BigDecimal(13.5)));
        } catch (Exception ex){}
    }
}
