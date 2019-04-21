package Day9_03312019;

import Reusable_Classes.Reusable_Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class yahoo extends Reusable_Methods {
    //declare your driver
    WebDriver driver;
    JavascriptExecutor jse;
    @BeforeMethod
    public void openBrowser() throws IOException {
       driver = navigate(driver,"https://www.yahoo.com");
       //define jse to be reuse
        jse = (JavascriptExecutor)driver;
    }//end of before

    @Test
    public void searchResult() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,8);
        //scroll to Yahoo logo
        //WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='uh-logo']")));
        Thread.sleep(3000);
        //jse.executeScript("arguments[0].scrollIntoView(true);",logo);
        //Threa
        List<WebElement> linkCount = driver.findElements(By.xpath("//span[contains(@class,'Mstart(2')]"));
        System.out.println("link count is " + linkCount.size());
        //enter keyword on your search field
        input(driver,"//*[@name='p']",0,"Cars","Search Field");
        //click on search icon
        click(driver,"//*[@id='uh-search-button']",0,"Search Button");
        //scroll to the bottom of the page to capture result
        Thread.sleep(2000);
        jse.executeScript("scroll(0,10000)");
        //capture my search result
        String result = getText(driver,"//*[@class='compPagination']",0,"Search Result");
        //split the result
        String[] arrayResult = result.split("Next");
        //print the search number
        System.out.println("Search number is " + arrayResult[1]);

    }//end of test

    @AfterMethod
    public void close(){
        driver.quit();
    }//end of after method


}//end of parent class

