//package com.balamurugan;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//
//public class WireMockTest2 {
//
//    public static void main(String[] args) {
//        WireMockServer wireMockServer = new WireMockServer(options().port(8080));
//        wireMockServer.start();
//
//        configureFor("localhost", 8080);
//
//        stubFor(post(urlEqualTo("/myservice"))
//                .withHeader("Content-Type", equalTo("text/xml"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "text/xml")
//                        .withBody("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ref=\"http://com/me/myservice/\">" +
//                                "<soapenv:Header/>" +
//                                "<soapenv:Body>" +
//                                "<ref:response>" +
//                                "<resImId>{{XPath request.body '/soapenv:Envelope/soapenv:Body/ref:invoke/Message/Ins/identification/imId/text()'}}" +
//                                "</resImId>" +
//                                "</ref:response>" +
//                                "</soapenv:Body>" +
//                                "</soapenv:Envelope>")
//                        .withTransformers("response-template")));
//
//        // Keep the application running to allow testing
//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
