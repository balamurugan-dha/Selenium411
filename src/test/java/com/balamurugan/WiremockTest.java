//package com.balamurugan;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import io.restassured.RestAssured;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//public class WiremockTest {
//
//    static WireMockServer wireMockServer;
//
//    @Before
//    public void startServer() {
//
//        // Start WireMock server on port 8080
//        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
//        wireMockServer.start();
//
//        // Configure the server to return a specific response for a GET request
//        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/api/mock"))
//                .willReturn(WireMock.aResponse()
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"This is a mocked response!\"}")
//                        .withStatus(200)
//                )
//        );
//
//        System.out.println("WireMock server started at port 8080");
//
//    }
//
//    @Test
//    public void apiTest() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//        given()
//                .when()
//                .get("/api/mock")
//                .then()
//                .statusCode(200)
//                .contentType("application/json")
//                .body("message", equalTo("This is a mocked response!"));
//    }
//
//    @After
//    public void tearDown() {
//        wireMockServer.stop();
//    }
//}
