package com.playground.soapdemo.model.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class NumberToWordsResult {
    @JacksonXmlText(value = true)
    private String answer;
}
