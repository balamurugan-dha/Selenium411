package com.balamurugan;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Test3 {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ref=\"http://com/me/myservice/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ref:invoke>" +
                "<Username>abc</Username>" +
                "<ReqType>Load</ReqType>" +
                "<ReqDataType>Product</ReqDataType>" +
                "<ReqSubType>hi</ReqSubType>" +
                "<Message>" +
                "<![CDATA[" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<Ins>" +
                "<documentType>" +
                "<name>hi</name>" +
                "</documentType> <publisher>" +
                "<system>" +
                "<source>XYZ</source>" +
                "</system> </publisher>" +
                "<event>" +
                "<date>2023-08-07</date>" +
                "<time>10:10:11</time>" +
                "<name>BMD to MDR Message</name>" +
                "</event>" +
                "<identification>" +
                "<imId>11111</imId>" +
                "</identification>" +
                "<content>" +
                "<Details> <id>2221</id>" +
                "</Details> </content>" +
                "</Ins>" +
                "]]></Message>" +
                "</ref:invoke>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
        NodeList messageNodes = doc.getElementsByTagName("Message");
        if (messageNodes.getLength() > 0) {
            String cdataContent = messageNodes.item(0).getTextContent();

            Document innerDoc = builder.parse(new InputSource(new StringReader(cdataContent)));
            NodeList imIdNodes = innerDoc.getElementsByTagName("imId");
            if (imIdNodes.getLength() > 0) {
                String imIdValue = imIdNodes.item(0).getTextContent();
                System.out.println("Value of <imId> tag: " + imIdValue);
            } else {
                System.out.println("No <imId> tag found");
            }
        } else {
            System.out.println("No <Message> tag found");
        }
    }
}