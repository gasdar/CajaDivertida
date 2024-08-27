package com.gasdar.app.funbox.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class RequestHelper {

    public static Map<String, Object> infoResponse(String message, Integer code) {
        Map<String, Object> json = new HashMap<>();
        json.put("responseMessage", message);
        json.put("responseCode", code);
        json.put("date", new Date().toString());
        return json;
    }

    public static ResponseEntity<?> getErrorsFromBody(BindingResult bindingResult) {
        Map<String, String> json = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            String errorField = error.getField();
            json.put(errorField, "El campo " + errorField + ", " + error.getDefaultMessage()); 
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
    }

}
