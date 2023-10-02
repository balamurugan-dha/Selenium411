//package com.balamurugan;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.StringReader;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//
//public class XMLParser {
//    public static void main(String[] args) {
//        WireMockServer wireMockServer = new WireMockServer();
//        wireMockServer.start();
//
//        configureFor("localhost", 8080);
//
//        stubFor(post(urlPathEqualTo("/getID"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "text/xml")
//                        .withBody("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ref=\"http://com/me/myservice/\">" +
//                                "<soapenv:Header/>" +
//                                "<soapenv:Body>" +
//                                "<ref:response>" +
//                                "&amp;lt;imId&amp;gt;{{extractedImId}}&amp;lt;/imId&amp;gt;" +
//                                "&amp;lt;clientId&amp;gt;1123456789&amp;lt;/clientId&amp;gt;" +
//                                "</ref:response>" +
//                                "</soapenv:Body>" +
//                                "</soapenv:Envelope>")
//                )
//        );
//    }
//
//    // Method to extract imId from nested XML and replace {{extractedImId}} in the response body
//    public static String extractImId(String nestedXml) {
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(new InputSource(new StringReader(nestedXml)));
//
//            String imId = doc.getElementsByTagName("imId").item(0).getTextContent();
//            return imId;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//}
