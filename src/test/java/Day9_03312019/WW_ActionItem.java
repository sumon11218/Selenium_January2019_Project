package Day9_03312019;

import Reusable_Classes.Reusable_Methods;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WW_ActionItem extends Reusable_Methods {
    //declare all the reusable variables among methods in class level
    WebDriver driver;
    Workbook readableFile;
    Sheet readableSheet;
    WritableWorkbook writableFile;
    WritableSheet writableSheet;
    int rows;
    Label label1, label2;
    String zipCode;

    @BeforeMethod
    public void openBrowser() throws IOException, BiffException {
        //define driver with navigate method
        driver = navigate(driver,"https://www.weightwatchers.com");
        //define readable workbook path
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\Weight_Watchers_Data_Driven.xls"));
        //now locate the readable sheet
        readableSheet = readableFile.getSheet(0);
        //physical count of the rows(non empty rows)
        rows = readableSheet.getRows();
        //create writable JxL file to duplicate the readable file
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\Weight_Watchers_Data_Driven_Results.xls"),readableFile);
        //locate the writable sheet to write back to it
        writableSheet = writableFile.getSheet(0);
    }//end of before method

    @Test
    public void testScenario() throws WriteException, InterruptedException {
        int i = 1;
        while(i<rows){

            zipCode = readableSheet.getCell(0,i).getContents();

            driver.navigate().to("https://weightwatchers.com");

            //verify the title of the page matches with your expected
            getTitle(driver,"Weight Loss & Welness Help");

            //click on the studio link
            click(driver,"//*[@class='find-a-meeting']",0,"Studio Link");

            //verify the zipcode page title matches with your expected
            getTitle(driver,"Studios & Meetings");

            //enter zipcode on search field
            input(driver,"//*[@id='meetingSearch']",0,zipCode,"Search Field");

            //click on Search Icon
            click(driver,"//*[@spice='SEARCH_BUTTON']",0,"Search Icon");

            //capture the first studio information
            String studioInfo = getText(driver,"//*[@class='location__container']",0,"Studio Info Textg");
            //click on the first studio link
            click(driver,"//*[@class='location__container']",0,"First Studio");
            //wait few seconds
            String operationHour = getText(driver, "//*[contains(@class,'currentday')]", 0, "Operation Hour Text");
            if(operationHour == null){
                operationHour = "Operation Hour doesn't Exist";
            }
            //create label for location and distance
            label1 = new Label(1,i,studioInfo);
            writableSheet.addCell(label1);

            //create label for current day opreation hour
            label2 = new Label(2,i,operationHour);
            writableSheet.addCell(label2);
            i++;
        }//end of while loop

    }//end of test method

    @AfterMethod
    public void closeBrowser() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();
    }//end of after method


}//end of parent class
