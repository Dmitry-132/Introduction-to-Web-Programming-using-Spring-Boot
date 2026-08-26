package org.skypro.skyshop.model.controller;


import org.skypro.skyshop.model.errors.ShopError;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //глобальный обработчик исключений для REST-контроллеров,Spring вызывает этот метод при возникновении всех исключений
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProductException e) {

        return new ResponseEntity<>(new ShopError("PRODUCT_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }  //HttpStatus.NOT_FOUND = код 404
}