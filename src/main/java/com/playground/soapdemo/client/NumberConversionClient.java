package com.playground.soapdemo.client;

import com.playground.soapdemo.client.helper.SoapHelper;
import com.playground.soapdemo.model.response.NumberConversionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class NumberConversionClient {

    @Value("${soap.endpoints.numberConversion}")
    private String endpoint;

    public NumberConversionResponse doPost(int number) {

        SoapHelper.callSoapWebService(endpoint);
        return null;
    }

}
