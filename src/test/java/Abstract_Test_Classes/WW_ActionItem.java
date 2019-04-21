package Abstract_Test_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WW_ActionItem extends Abstract_Class {
    //declare all the reusable variables among methods in class level
    Workbook readableFile;
    Sheet readableSheet;
    WritableWorkbook writableFile;
    WritableSheet writableSheet;
    int rows;
    Label label1, label2;
    String zipCode;

    @Test
    public void TC02_CaptureOperationHour() throws WriteException, InterruptedException, IOException, BiffException {

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



        int i = 1;
        while(i<=1){

            zipCode = readableSheet.getCell(0,i).getContents();

            driver.navigate().to("https://weightwatchers.com");

            //verify the title of the page matches with your expected
            getTitle(driver,logger,"Weight Loss & Welness Help");

            //click on the studio link
            click(driver,logger,"//*[@class='find-a-meeting']",0,"Studio Link");

            //verify the zipcode page title matches with your expected
            getTitle(driver,logger,"Studios & Meetings");

            //enter zipcode on search field
            input(driver,logger,"//*[@id='meetingSearch']",0,zipCode,"Search Field");

            //click on Search Icon
            click(driver,logger,"//*[@spice='SEARCH_BUTTON']",0,"Search Icon");

            //capture the first studio information
            String studioInfo = getText(driver,logger,"//*[@class='location__container']",0,"Studio Info Textg");
            //click on the first studio link
            click(driver,logger,"//*[@class='location__container']",0,"First Studio");
            //wait few seconds
            String operationHour = getText(driver, logger,"//*[contains(@class,'currentday')]", 0, "Operation Hour Text");
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

        writableFile.write();
        writableFile.close();
        readableFile.close();

    }//end of test method

}//end of parent class
