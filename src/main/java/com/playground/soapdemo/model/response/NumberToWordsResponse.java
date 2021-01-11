package com.playground.soapdemo.model.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class NumberToWordsResponse {
    @JacksonXmlProperty(localName = "NumberToWordsResult")
    private NumberToWordsResult numberToWordsResult;
}
