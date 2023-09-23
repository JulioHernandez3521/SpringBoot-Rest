package com.app.cias.excepciones;

import org.hibernate.PropertyValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BaseController {
    private Logger log = LoggerFactory.getLogger(BaseController.class);

    //** Responde errores provocados por @Valid y validaciones del modelo o entidad
    /**
     *  Metodo para manejo de excepciones de la clase MethodArgumentNotValidException que vienen provocados
     *  por Javax Vlidators del modelo rquerido y que usan el @Valid en el controlador
     * @param ex La excepcion que se produce
     * @return Unna respuesta Con un status Con o sin body con header o sin ellos
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidateExceptionsModels(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        log.error("Captura de errores en Modelo");
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });
        return errors;
    }

    /**
     * Metodo para validar excepciones provocadas por JPA segun las propiedades del modelo
     * Puede enviar los errores por header o body
     * @param ex La clase de la excepcion provocada
     * @return Un Response con los errores status 400
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PropertyValueException.class)
    public Map<String,Object> handleValidateJPAExcepcion(PropertyValueException ex){
        Map<String,Object> errors = new HashMap<>();
        log.error("Captura de errores en Modelo");
        log.error(ex.getPropertyName());
        log.error(ex.getMessage());
        log.error(ex.getEntityName());
        log.error(ex.getLocalizedMessage());
        errors.put("Property",ex.getPropertyName());
        errors.put("Entity",ex.getEntityName());
        errors.put("Error",ex.getMessage());
        errors.put("Status",HttpStatus.BAD_REQUEST);
        errors.put("StatusCode",HttpStatus.BAD_REQUEST.value());
        errors.put("message",ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String,Object> handleValidateJPAExcepcion(DataIntegrityViolationException ex){
        Map<String,Object> errors = new HashMap<>();
        log.error("Captura de errores en Modelo");
        log.error(ex.getMessage());
        log.error(ex.getLocalizedMessage());
        errors.put("Error",ex.getMessage());
        errors.put("Status",HttpStatus.BAD_REQUEST);
        errors.put("StatusCode",HttpStatus.BAD_REQUEST.value());
        errors.put("message",ex.getLocalizedMessage());

        return errors;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleValidateJPAExcepcion(Exception ex){
        Map<String,Object> errors = new HashMap<>();
        log.error("Captura de errores en Modelo");
        log.error(ex.getMessage());
        log.error(ex.getLocalizedMessage());
        log.error(ex.getClass().getName());
        errors.put("Error",ex.getMessage());
        errors.put("Status",HttpStatus.INTERNAL_SERVER_ERROR);
        errors.put("StatusCode",HttpStatus.INTERNAL_SERVER_ERROR.value());
        errors.put("message",ex.getLocalizedMessage());
        return errors;
    }


}
