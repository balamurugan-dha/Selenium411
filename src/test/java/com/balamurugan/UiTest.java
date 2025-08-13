package com.balamurugan;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.network.Network;

import java.time.Duration;
import java.util.Optional;

public class UiTest {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://www.lambdatest.com/blog/selenium-manager-in-selenium-4-11-0/");
        driver.quit();

//        captureNetworkLogs();
    }

    public static void captureNetworkLogs() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(20L));
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();

        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Listen to request and response events
        devTools.addListener(Network.requestWillBeSent(), (requestWillBeSent) -> {
            if (requestWillBeSent.getRequest().getUrl().contains("/specialty")) {
                System.out.println("Request URL: " + requestWillBeSent.getRequest().getUrl());
                System.out.println("Request Post Data: " + requestWillBeSent.getRequest().getPostData());
                System.out.println("--------------------------------------------------------------------------------------");
            }
        });

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            try {
                Network.GetResponseBodyResponse responseBody = devTools.send(Network.getResponseBody(responseReceived.getRequestId()));
                if (responseReceived.getResponse().getUrl().contains("/specialty")) {
                    if (responseBody.getBody() != null) {
                        System.out.println("Response Body: " + responseBody.getBody());
                    }
                }
            } catch (Exception e) {
                if (responseReceived.getResponse().getUrl().contains("/specialty")) {
                    System.out.println("Error fetching response body for: " + responseReceived.getResponse().getUrl() + ". Error: " + e.getMessage());
                }
            }
        });

        driver.get("https://www.anthem.com/find-care/");
        driver.manage().window().maximize();

        driver.findElement(By.id("txt-alpha-prefix")).sendKeys("XVV");
        driver.findElement(By.id("btn-prefix-search")).click();

        driver.findElement(By.id("zipCodeSrchID")).sendKeys("New Yor, NY");
        driver.findElement(By.id("lnk-county-0")).click();
        driver.findElement(By.id("lnk-providerType-0")).click();

        driver.quit();
    }

}

import org.openqa.selenium.By;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpRequest;

import java.util.function.BiPredicate;

public class BasicAuthBidiExample {

    public static void main(String[] args) {
        // Ensure you have a BiDi-capable driver like ChromeDriver, EdgeDriver, or FirefoxDriver
        WebDriver driver = new ChromeDriver();

        // 1. Define the URI for the protected resource
        String authUrl = "the-internet.herokuapp.com/basic_auth";

        // 2. Create a BiPredicate to filter network requests.
        // This predicate checks if the request URI contains the target URL.
        BiPredicate<HttpRequest, HttpHandler> predicate =
                (req, next) -> req.getUri().contains(authUrl);

        // 3. Use a try-with-resources block to manage the NetworkInterceptor
        // This ensures the interceptor is automatically closed.
        try (NetworkInterceptor interceptor = new NetworkInterceptor(driver, predicate)) {

            // 4. Register the authenticator with your credentials.
            // When a request matches the predicate, these credentials will be supplied.
            interceptor.register(new UsernameAndPassword("admin", "admin"));

            // 5. Navigate to the protected page.
            // The browser will issue an authentication challenge, which Selenium's
            // NetworkInterceptor will handle automatically in the background.
            driver.get("https://the-internet.herokuapp.com/basic_auth");

            // 6. Verify that authentication was successful
            String successMessage = driver.findElement(By.tagName("p")).getText();
            System.out.println("Page content after authentication: " + successMessage);
            // In a real test, you would use an assertion here:
            // Assert.assertEquals("Congratulations! You must have the proper credentials.", successMessage);
        }

        // Close the browser session
        driver.quit();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Filter;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpRequest;

import java.util.function.Predicate;

public class ManualAuthBidiExample {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String authUrl = "the-internet.herokuapp.com/basic_auth";

        // 1. Define a predicate to identify the requests that need authentication.
        Predicate<HttpRequest> authPredicate = req -> req.getUri().contains(authUrl);

        // 2. Create a Filter to build your custom authentication logic.
        // The 'next' parameter is the next handler in the chain, which we must call.
        Filter authFilter = next -> request -> {

            // 3. Check if the current request matches our predicate.
            if (authPredicate.test(request)) {
                // If it matches, create the credentials
                UsernameAndPassword credentials = new UsernameAndPassword("admin", "admin");

                // 4. Manually add the 'Authorization' header to the request.
                // HttpRequest objects are immutable, so we create a new one with the added header.
                HttpRequest authenticatedRequest = request.withHeader(
                    "Authorization", 
                    credentials.getAuthorization()
                );

                // 5. Execute the MODIFIED request by passing it to the next handler.
                return next.execute(authenticatedRequest);
            }

            // 6. If the request does not need auth, pass it through unmodified.
            return next.execute(request);
        };

        // 7. Create the NetworkInterceptor, passing it the custom Filter.
        try (NetworkInterceptor interceptor = new NetworkInterceptor(driver, authFilter)) {
            System.out.println("Navigating with manual authentication filter...");
            driver.get("https://the-internet.herokuapp.com/basic_auth");

            String successMessage = driver.findElement(By.tagName("p")).getText();
            System.out.println("Page content after authentication: " + successMessage);
        }

        driver.quit();
    }
}



import org.openqa.selenium.By;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpRequest;

import java.util.function.BiPredicate;

public class LatestSeleniumAuthExample {

    public static void main(String[] args) {
        // Ensure you have a BiDi-capable driver like ChromeDriver or EdgeDriver
        WebDriver driver = new ChromeDriver();

        // 1. Define the URI for the protected resource
        String authUrl = "the-internet.herokuapp.com/basic_auth";

        // 2. Create a BiPredicate to filter network requests
        BiPredicate<HttpRequest, HttpHandler> predicate =
                (req, next) -> req.getUri().contains(authUrl);

        // 3. Use a try-with-resources block for the NetworkInterceptor.
        // This is the standard pattern shown in the official documentation.
        try (NetworkInterceptor interceptor = new NetworkInterceptor(driver, predicate)) {

            // 4. Register the authenticator with your credentials.
            // The .register() method is the correct and standard way to do this.
            interceptor.register(new UsernameAndPassword("admin", "admin"));

            // 5. Navigate to the protected page
            driver.get("https://the-internet.herokuapp.com/basic_auth");

            // 6. Verify that authentication was successful
            String successMessage = driver.findElement(By.tagName("p")).getText();
            System.out.println("Page content after authentication: " + successMessage);
            // In a real test, you would use an assertion here.
        }

        // Close the browser session
        driver.quit();
    }
}


