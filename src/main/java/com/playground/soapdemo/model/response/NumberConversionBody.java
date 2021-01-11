package com.playground.soapdemo.model.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
public class NumberConversionBody {
    @JacksonXmlProperty(localName = "soapenv:NumberToWordsResponse", namespace = "http://www.dataaccess.com/webservicesserver/")
    private NumberToWordsResponse numberToWordsResponse;
}
