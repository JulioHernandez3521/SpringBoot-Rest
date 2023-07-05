package com.app.cias.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BadALertRequest{

    private final static Logger log = LoggerFactory.getLogger(BadALertRequest.class);

    public static ResponseEntity<?> BadALertRequest(String mensaje, String clase, String causa, HttpStatus estatus){
        Map<String,Object> response = new HashMap<>();
        response.put("message",mensaje);
        response.put("cause",causa);
        response.put("entity",clase);

        log.error(response.toString());

        return new ResponseEntity<Map<String, Object>>(response, estatus);
    }

}
