package com.app.userm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class ResponceModel<T> {

    private HttpStatus status;
    private LocalDate timestamp;
    private String message;
    private T data;


    // Constructor with all fields
    public ResponceModel(HttpStatus status, String message, T data) {
        this.timestamp = LocalDate.now();
        this.status = status;
        this.message = message;
        this.data = data;
        // Captures current time
    }

    // Constructor without data
    public ResponceModel(HttpStatus status, String message) {
        this.timestamp = LocalDate.now();
        this.status = status;
        this.message = message;
       // Captures current time
    }

    // Static factory methods for common responses

    public static <T> ResponceModel<T> getSuccessResponse(String message, T data) {
        return new ResponceModel<>(HttpStatus.OK, message, data);
    }

    public static ResponceModel<Void> getBadRequest(String message) {
        return new ResponceModel<>(HttpStatus.BAD_REQUEST, message);
    }

    public static ResponceModel<Void> getInternalServerError(String message) {
        return new ResponceModel<>(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static <T> ResponceModel<T> getCustomError(String message, HttpStatus status, T data) {
        return new ResponceModel<>(status, message, data);
    }

    public static ResponceModel<Void> getCustomError(String message, HttpStatus status) {
        return new ResponceModel<>(status, message);
    }
}
