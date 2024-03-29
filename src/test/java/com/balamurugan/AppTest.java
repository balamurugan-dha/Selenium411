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

/**
Function GetRGBColor(cell As Range) As String
    Dim r As Integer
    Dim g As Integer
    Dim b As Integer
    
    ' Check if cell has fill color
    If cell.Interior.ColorIndex <> xlNone Then
        r = cell.Interior.Color Mod 256
        g = (cell.Interior.Color \ 256) Mod 256
        b = (cell.Interior.Color \ 65536) Mod 256
        GetRGBColor = "R:" & r & ", G:" & g & ", B:" & b
    Else
        GetRGBColor = "No fill color"
    End If
End Function
    */

/**
Function GetRGBColor(cell As Range) As String
    Dim r As Integer
    Dim g As Integer
    Dim b As Integer
    Dim colorRGB As Long
    
    ' Check if cell has fill color
    If cell.Interior.ColorIndex <> xlNone Then
        ' Get the RGB color value
        colorRGB = cell.Interior.Color
        r = colorRGB Mod 256
        g = (colorRGB \ 256) Mod 256
        b = (colorRGB \ 65536) Mod 256
        GetRGBColor = "R:" & r & ", G:" & g & ", B:" & b
    Else
        GetRGBColor = "No fill color"
    End If
End Function
    */

/**
Sub HideColumns()

    Dim referenceColor As Long
    referenceColor = Range("A1").Interior.Color

    Dim col As Range
    For Each col In ActiveSheet.UsedRange.Columns
        Dim cell As Range
        Dim hideColumn As Boolean
        hideColumn = False
        For Each cell In col.Cells
            If cell.Interior.Color <> referenceColor Then
                hideColumn = True
                Exit For
            End If
        Next cell
        If hideColumn Then
            col.EntireColumn.Hidden = True
        End If
    Next col

End Sub
    */

/**
Sub HideColumns()

    Dim referenceColor As Long
    referenceColor = Range("A1").Interior.Color

    Dim col As Range
    For Each col In ActiveSheet.UsedRange.Columns
        Dim cell As Range
        Dim hideColumn As Boolean
        hideColumn = True
        For Each cell In col.Cells
            If cell.Interior.Color = referenceColor Then
                hideColumn = False
                Exit For
            End If
        Next cell
        If hideColumn Then
            col.EntireColumn.Hidden = True
        End If
    Next col

End Sub
    */

/**
Sub HideColumns()

    Dim referenceColor As Long
    referenceColor = Range("A1").Interior.Color

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
        col.EntireColumn.Hidden = hideColumn
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
        col.EntireColumn.Hidden = hideColumn
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
        If hideColumn Then col.EntireColumn.Hidden = True
    Next col

End Sub
    */
