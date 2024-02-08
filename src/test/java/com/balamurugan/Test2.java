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
