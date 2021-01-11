package com.playground.soapdemo.service;

import com.playground.soapdemo.client.NumberConversionClient;
import com.playground.soapdemo.model.response.NumberConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class NumberConversionService {

    private NumberConversionClient _client;

    @Autowired
    public NumberConversionService(NumberConversionClient client) {
        _client = client;
    }

    public ResponseEntity<NumberConversionResponse> getNumberToWords(int number) {
        NumberConversionResponse res = _client.doPost(number);

        return ResponseEntity.ok()
                .body(res);
    }
}
