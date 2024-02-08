package com.balamurugan;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

/**
Sub HideColumnsBasedOnColor()
    Dim rng As Range
    Dim cell As Range
    Dim lastRow As Long
    Dim lastColumn As Long
    Dim col As Long
    
    'Change Sheet1 to the name of your sheet
    Set rng = ThisWorkbook.Sheets("Sheet1").UsedRange
    
    lastRow = rng.Rows.Count
    lastColumn = rng.Columns.Count
    
    'Loop through each column
    For col = 1 To lastColumn
        'Check if at least one cell in the column is not green
        For Each cell In rng.Columns(col).Cells
            If cell.Interior.Color <> RGB(0, 255, 0) Then 'Green color (RGB: 0, 255, 0)
                'If not green, hide the entire column and exit the loop
                Columns(col).Hidden = True
                Exit For
            End If
        Next cell
    Next col
End Sub
    */
