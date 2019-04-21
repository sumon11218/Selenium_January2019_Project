package day10_0406;

import Reusable_Classes.Reusable_Methods;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

public class Express_Action_Item extends Reusable_Methods {

    //declare all the shared variables among methods
    WebDriver driver;
    JavascriptExecutor jse;
    Workbook readableFile;
    Sheet readableSheet;
    WritableWorkbook writableFile;
    WritableSheet writableSheet;
    int rows;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void openBrowser() throws IOException, BiffException {
        //call the navigate method for chrome driver
        driver = navigate(driver,"https://www.express.com");
        //locate the readable file
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\Express_Data_Driven.xls"));
        //locate readable sheet
        readableSheet = readableFile.getSheet(0);
        //get physical count of rows
        rows = readableSheet.getRows();
        //define the writable file here
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\Express_Data_Driven_Result.xls"),readableFile);
       //define the writable sheet to write back later
        writableSheet = writableFile.getSheet(0);
        //define javascriptexecutor here
        jse = (JavascriptExecutor)driver;
    }//end of before method

    @Test
    public void expressCheckout() throws InterruptedException, WriteException {
        //define soft assert
        for(int i = 1; i==1;i++){
            //this is for gettting all size from excel
            String size = readableSheet.getCell(0,i).getContents();
            //this is for quantity from excel
            String quantity = readableSheet.getCell(1,i).getContents();
            //this is for first name from excel
            String firstName = readableSheet.getCell(2,i).getContents();
            //this is for last name from excel
            String lastName = readableSheet.getCell(3,i).getContents();
            //this is for email from excel
            String email = readableSheet.getCell(4,i).getContents();
            //this is for phone from excel
            String phone = readableSheet.getCell(5,i).getContents();
            //this is for address from excel
            String address = readableSheet.getCell(6,i).getContents();
            //this is for zip code from excel
            String zipCode = readableSheet.getCell(7,i).getContents();
            //this is for city from excel
            String city = readableSheet.getCell(8,i).getContents();
            //this is for state from excel
            String state = readableSheet.getCell(9,i).getContents();
            //this is for card number from excel
            String cardNumber = readableSheet.getCell(10,i).getContents();

            /** Your Test Steps starts here **/
            //navigate to express
            driver.navigate().to("https://www.express.com");
            //verify the title matches
            //Assert.assertEquals(driver.getTitle(),"Mens & Women's Clothing");
            //soft assert
            softAssert.assertEquals("Mens & Women's Clothing",driver.getTitle());
            //getTitle(driver,"Men's and Women's Clothing");
            //hover to Women tab
            mouseHover(driver,"//*[@href='/womens-clothing']",0,"Women Tab");
            //hover to dresses tab
            mouseHover(driver,"//*[@href='/womens-clothing/dresses/cat550007']",0,"Dresses Link");
            //click on shop by style link
            click(driver,"//*[contains(text(),'Shop by Style')]",0,"Shop by Style Link");
            //scroll down about 400 pixels
            Thread.sleep(3000);
            jse.executeScript("scroll(0,400)");
            //click on first image for the dress
            click(driver,"//*[contains(@src,'https://images.express.com/is/image/expressfashion')]",0,"First Image");
            //click on the size
            click(driver,"//*[@value='"+size+"']",0,"Sizes");
            //click on add to bag button
            click(driver,"//*[text()='Add to Bag']",0,"Add to Bag Button");
            //click on checkout
            click(driver,"//*[text()='CHECKOUT']",0,"Checkout Button");
            //select quantity from dropdown
            selectByText(driver,"//*[@id='qdd-0-quantity']",0,quantity,"Quantity Dropdown");
            //click on checkout again
            click(driver,"//*[text()='Checkout']",0,"Checkout Button");
            //click on continue as guest
            click(driver,"//*[text()='Continue as Guest']",0,"Continue as Guest Button");
            //enter first name
            input(driver,"//*[@name='firstname']",0, firstName,"First Name Field");
            //enter last name
            input(driver,"//*[@name='lastname']",0, lastName,"Last Name Field");
            //enter email
            input(driver,"//*[@type='email']",0, email,"Email Field");
            //re-enter email again
            input(driver,"//*[@name='confirmEmail']",0, email,"Email Again Field");
            //enter phone
            input(driver,"//*[@name='phone']",0, phone,"Phone Field");
            //click on continue button twice
            click(driver,"//*[text()='Continue']",0,"Continue Button");
            click(driver,"//*[text()='Continue']",0,"Continue Button");
            //enter address
            input(driver,"//*[@name='shipping.line1']",0,address,"Address Field");
            //enter zipcode
            input(driver,"//*[@name='shipping.postalCode']",0,zipCode,"Zipcode Field");
            //enter city
            input(driver,"//*[@name='shipping.city']",0,city,"City Field");
            //select New York as dropdown for all iteration
            selectByText(driver,"//*[@name='shipping.state']",0,state,"State Dropdown");
            //click on continue
            click(driver,"//*[text()='Continue']",0,"Continue Button");
            //enter invallid cc
            input(driver,"//*[@name='creditCardNumber']",0,cardNumber,"CC Field");
            //click on place order
            click(driver,"//*[text()='Place Order']",0,"Place Order Button");
            //capture the error message
            String errorMessage = getText(driver,"//*[@class='dOxMH labelError']",0,"CC Error Message");
            //create label to add to writable sheet
            Label label = new Label(11,i,errorMessage);
            //add label back to writable sheet
            writableSheet.addCell(label);
            //delete all cookies
            driver.manage().deleteAllCookies();

        }
        softAssert.assertAll();


    }//end of test method

    @AfterMethod
    public void closeSession() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();

    }//end of after method



}//end of parent class
