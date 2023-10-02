package com.balamurugan;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Test2 {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(20L));
        ChromeDriver driver = new ChromeDriver(options);

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
