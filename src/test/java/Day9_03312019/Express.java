package Day9_03312019;

import Reusable_Classes.Reusable_Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Express extends Reusable_Methods {
    //declare your driver
    WebDriver driver;
    JavascriptExecutor jse;
    @BeforeMethod
    public void openBrowser() throws IOException {
       driver = navigate(driver,"https://www.express.com");
       //define jse to be reuse
        jse = (JavascriptExecutor)driver;
    }//end of before

    @Test
    public void checkoutProcess() throws InterruptedException {
        mouseHover(driver,"//*[@href='/womens-clothing']",0,"Women Clothing Tab");
        mouseHover(driver,"//*[@href='/womens-clothing/dresses/cat550007']",0,"Women Link");
        click(driver,"//*[contains(@aria-label,'Shop by Style')]",0,"Shop By Style Link");
        Thread.sleep(3000);
        jse.executeScript("scroll(0,400)");
        click(driver,"//*[contains(@src,'https://images.express.com/is/image/expressfashion')]",0,"First Image");
        //click on first size
        click(driver,"//*[@value='00']",0,"First Size");
        //click on Add to Bag
        click(driver,"//*[text()='Add to Bag']",0,"Add To Bag Button");
        //click on checkout
        click(driver,"//*[text()='CHECKOUT']",0,"Checkout Button");
        //select quantity
        selectByText(driver,"//*[@id='qdd-0-quantity']",0, "2","Qunantity Dropdown");
        //click on  checkout
        click(driver,"//*[text()='Checkout']",0,"Checkout Button");
        //click on continue as guest button
        click(driver,"//*[text()='Continue as Guest']",0,"Continue As Guest Button");

    }//end of test

    @AfterMethod
    public void close(){
        //driver.quit();
    }//end of after method


}//end of parent class

