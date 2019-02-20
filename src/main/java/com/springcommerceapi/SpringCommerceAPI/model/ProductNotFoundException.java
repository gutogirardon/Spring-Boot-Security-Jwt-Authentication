package com.springcommerceapi.SpringCommerceAPI.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND )
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String exception) {
        super(exception);
    }
}
