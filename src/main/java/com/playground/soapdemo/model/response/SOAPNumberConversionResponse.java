package com.playground.soapdemo.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@JacksonXmlRootElement(localName = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class SOAPNumberConversionResponse {
    @JacksonXmlProperty(localName = "Body")
    private NumberConversionBody body;
}
