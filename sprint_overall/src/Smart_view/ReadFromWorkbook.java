package Smart_view;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ReadFromWorkbook {
                public static WebDriver driver;
                public static Sheet oldexcel;
                public static WritableSheet newexcel;
                public static WritableWorkbook copyfromexcel;
                
                
                @BeforeSuite
                public void filesfromexcel() throws BiffException, IOException,WriteException,FileNotFoundException {
                
                FileInputStream readingfromexcel = new FileInputStream("testdata.xls");
                
                Workbook w = Workbook.getWorkbook(readingfromexcel);

                  oldexcel = w.getSheet(0);// checks for the exact excel sheet, with "0" considering as the first sheet
                  Workbook workbook2 = Workbook.getWorkbook(new File("testdata.xls"));//original sheet
                  copyfromexcel = Workbook.createWorkbook(new File("testdata1.xls"), workbook2);// creates a new excel sheet with the result
                  newexcel = copyfromexcel.getSheet(0);
}
                    
                     
                     
                } 
