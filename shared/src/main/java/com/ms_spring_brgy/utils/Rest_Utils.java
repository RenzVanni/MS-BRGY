package com.ms_spring_brgy.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Rest_Utils {
    //find all
    public static <T> ResponseEntity<List<T>> RestFindAll(Supplier<List<T>> findAllOperation) {
        List<T> response = findAllOperation.get();
        if(response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //find by id
    public static <T> ResponseEntity<T> RestFindById(Supplier<T> findByIdOperation) {
        T response = findByIdOperation.get();
        if(Objects.isNull(response)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // add
    public static <T> ResponseEntity<T> RestAdd(Supplier<T> addOperation) {
        T response = addOperation.get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // add list of multipart
    public static <T> ResponseEntity<List<T>> RestAddMultipart(Supplier<List<T>> service) {
        List<T> response = service.get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update
    public static <T> ResponseEntity<T> RestUpdate(Supplier<T> updateOperation) {
        T response = updateOperation.get();
        if(Objects.isNull(response)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    //delete
    public static ResponseEntity<String> RestDelete(Supplier<String> deleteOperation)  {
        String response = deleteOperation.get();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
