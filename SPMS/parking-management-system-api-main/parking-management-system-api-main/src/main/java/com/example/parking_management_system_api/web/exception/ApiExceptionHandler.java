package com.example.parking_management_system_api.web.exception;

import com.example.parking_management_system_api.exception.*;
import com.example.parking_management_system_api.exception.IllegalStateException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorMessage> illegalStateException(IllegalStateException ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }


   @ExceptionHandler(InvalidFieldException.class)
   public ResponseEntity<Map<String, String>> invalidFieldException (InvalidFieldException e){
       log.error("API error - ", e);
       Map<String, String> errorResponse = new HashMap<>();
       errorResponse.put("error", e.getMessage());

       return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

   }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Map<String, String>> vehicleNotFoundException (VehicleNotFoundException e){
        log.error("API error - ", e);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidVehicleCategoryAndTypeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidVehicleCategoryAndTypeException(InvalidVehicleCategoryAndTypeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
