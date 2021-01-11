package com.playground.soapdemo.controller;

import com.playground.soapdemo.model.response.NumberConversionResponse;
import com.playground.soapdemo.service.NumberConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/number/")
public class NumberConversionController {

    @Autowired
    private NumberConversionService _service;

    @GetMapping("convert/{number}")
    public ResponseEntity<NumberConversionResponse> getNumberToWords(@PathVariable int number) {
        return _service.getNumberToWords(number);
    }

}
