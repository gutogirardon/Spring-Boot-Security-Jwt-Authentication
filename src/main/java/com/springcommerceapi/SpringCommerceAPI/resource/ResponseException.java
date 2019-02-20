package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.ErroComposer;
import com.springcommerceapi.SpringCommerceAPI.model.ProductNotFoundException;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErroComposer> handleProductNotFound(ProductNotFoundException exception, WebRequest request){
        ErroComposer error = new ErroComposer(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
