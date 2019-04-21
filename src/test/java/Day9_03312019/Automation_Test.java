package Day9_03312019;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static Reusable_Classes.Reusable_Methods.click;
import static Reusable_Classes.Reusable_Methods.mouseHover;
import static Reusable_Classes.Reusable_Methods.navigate;

public class Automation_Test {
    WebDriver driver;
    JavascriptExecutor jse;
    @BeforeMethod
    public void openBrowser() throws IOException, BiffException {
        //define driver with navigate method
        driver = navigate(driver,"http://automationpractice.com/index.php");
    }//end of before method

    @Test
    public void checkoutProcess() throws InterruptedException {
       //hover to dresses tab
        click(driver,"//*[@id='block_top_menu']/ul/li[1]",0,"Dresses Tab");
      //click on dresses tab on the left side with + icon
        click(driver,"//*[contains(@title,'Find your favorites dresses']",0,"Dresses Icon");
       //click on summer dress tab with + icon
        click(driver,"//*[contains(text(),'Summer Dresses']",0,"Summer Dresses Icon");
        Thread.sleep(2000);
        jse.executeScript("scroll(0,700)");
        mouseHover(driver,"//*[@title='Printed Summer Dress']",0,"Summer Dress Image");
    }//end of test






}//end of parent class
