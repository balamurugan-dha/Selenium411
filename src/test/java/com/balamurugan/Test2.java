package com.balamurugan;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test2 {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.FINE);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(Level.FINE);
        });

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

/**
Sub HideColumns()
    Dim referenceColor As Long
    referenceColor = Range("M17").Interior.Color

    Dim col As Range
    Dim cell As Range
    Dim hideColumn As Boolean

    For Each col In ActiveSheet.UsedRange.Columns
        hideColumn = True ' Assume column will be hidden unless proven otherwise
        For Each cell In col.Cells
            If cell.Interior.Color = referenceColor Then
                hideColumn = False ' At least one cell matches, so don't hide column
                Exit For
            End If
        Next cell
        If hideColumn Then
            col.EntireColumn.Hidden = True
            MsgBox "Column " & col.Column & " hidden."
        End If
    Next col
End Sub
    */

/**
Sub HideColumns()
    Dim referenceColor As Long
    referenceColor = Range("M17").Interior.Color

    Dim col As Range
    Dim cell As Range
    Dim hideColumn As Boolean

    For Each col In ActiveSheet.UsedRange.Columns
        hideColumn = True ' Assume column will be hidden unless proven otherwise
        For Each cell In col.Cells
            If cell.Interior.Color = referenceColor Then
                hideColumn = False ' At least one cell matches, so don't hide column
                Exit For
            End If
        Next cell
        If hideColumn Then
            col.EntireColumn.Hidden = True
            MsgBox "Column " & col.Column & " hidden."
        End If
    Next col
End Sub
    */

/**

CSS version
     -------------

     (function(sel){
     var el = document.querySelector(sel);
     if(!el) return 'NOT_FOUND';
     var chain = [];
     var a = el;
     while(a){
     var s = window.getComputedStyle(a);
     chain.push({tag: a.tagName, id: a.id || null, display: s.display, visibility: s.visibility});
     a = a.parentElement;
     }
     return chain;
     })("div.section:nth-of-type(4) > button");


     xpath version
     --------------
     (function(xpath){
     // 1. Use document.evaluate instead of querySelector
     var result = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);

     // 2. Extract the specific node from the result wrapper
     var el = result.singleNodeValue;

     // 3. The rest of your logic remains the same
     if(!el) return 'NOT_FOUND';

     var chain = [];
     var a = el;
     while(a){
     // Get computed style
     var s = window.getComputedStyle(a);

     // Add to chain
     chain.push({
     tag: a.tagName,
     id: a.id || null,
     display: s.display,
     visibility: s.visibility
     });

     // Move up
     a = a.parentElement;
     }
     return chain;

     // Pass your XPath string here
     })("//div[contains(@class, 'section')][4]/button");
*/
