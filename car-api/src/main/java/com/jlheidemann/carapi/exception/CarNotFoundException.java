package com.jlheidemann.carapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jean.heidemann
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Car not found")
public class CarNotFoundException extends RuntimeException {
}
