package com.playground.soapdemo.client.helper;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.playground.soapdemo.model.response.NumberConversionBody;
import com.playground.soapdemo.model.response.NumberToWordsResponse;
import com.playground.soapdemo.model.response.NumberToWordsResult;
import com.playground.soapdemo.model.response.SOAPNumberConversionResponse;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;

public class SoapHelper {
    public static void callSoapWebService(String endpointUrl) {
        try {
            // Create the SOAP Connection:
            SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = scf.createConnection();

            //Send SOAP Message to SOAP Server
            SOAPMessage response = soapConnection.call(createSOAPRequest(), endpointUrl);
            SOAPBody soapBody = response.getSOAPBody();

            // Print the response for debugging:
            System.out.println("Response:");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            response.writeTo(baos);
            String stringifiedBody = new String(baos.toByteArray());
            XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            NumberToWordsResult result = new NumberToWordsResult();
            result.setAnswer("500");
            NumberToWordsResponse res = new NumberToWordsResponse();
            res.setNumberToWordsResult(result);
            NumberConversionBody body = new NumberConversionBody();
            body.setNumberToWordsResponse(res);
            SOAPNumberConversionResponse sresponse = new SOAPNumberConversionResponse();
            sresponse.setBody(body);

            String str = mapper.writeValueAsString(sresponse);
            System.out.println(">> HOT BINGO: \n" + str + "\n <<");

            SOAPNumberConversionResponse deserializeResponse = mapper.readValue(
                    stringifiedBody,
                    SOAPNumberConversionResponse.class
            );
//            System.out.println("Body: \n" + stringifiedBody);
//            System.out.println("Answer: " + deserializeResponse.getBody().getNumberToWordsResponse().getNumberToWordsResult().getAnswer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message = mf.createMessage();

        createSOAPEnvelope(message);

        message.saveChanges();
        /* Print the request message out for debugging purposes */
        message.writeTo(System.out);
        System.out.println("\n");

        return message;
    }

    private static void createSOAPEnvelope(SOAPMessage message) {
        /*
        We want to match this message:

        <?xml version="1.0" encoding="utf-8"?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
          <soap:Body>
            <NumberToWords xmlns="http://www.dataaccess.com/webservicesserver/">
              <ubiNum>500</ubiNum>
            </NumberToWords>
          </soap:Body>
        </soap:Envelope>
         */

        try {
            String prefix = "soap";
            // Define prefixes:
            SOAPEnvelope env = message.getSOAPPart().getEnvelope();
            env.setPrefix(prefix);
            env.removeNamespaceDeclaration("SOAP-ENV");
            SOAPBody body = message.getSOAPBody();
            body.setPrefix(prefix);
            // Remove headers to match our message:
            message.getSOAPHeader().detachNode();

            // Now add elements to our body to complete the request:
            QName numberToWordsName = new QName("http://www.dataaccess.com/webservicesserver/", "NumberToWords");
            SOAPElement numberToWordsElem = body.addChildElement(numberToWordsName);
            SOAPElement ubiNumElem = numberToWordsElem.addChildElement("ubiNum");
            ubiNumElem.addTextNode("500"); // TODO: Inject data here

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
