package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Reusable_Methods_Loggers {

    static int timeOut = 20;

    //method for navigating to a site
    public static WebDriver navigate(WebDriver driver,String url) throws IOException, InterruptedException {
        //define the path of the chrome driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver1.exe");

        //quit all open chrome browsers
        Runtime.getRuntime().exec("taskkill /im chromedriver1.exe /f /t");
        Thread.sleep(2800);
        //seeting up the chromeoptions
        ChromeOptions options = new ChromeOptions();
        //add the precondition arguments
        options.addArguments("start-maximized","incognito","disable-infobars");

        //define the chrome web driver
        driver = new ChromeDriver(options);

        //navigate to usps.com
        driver.navigate().to(url);

        return driver;
    }

    //method to click on an element
    public static void click(WebDriver driver, ExtentTest logger,String locator, int index,String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        System.out.println("Clicking on element " + elementName);
        logger.log(LogStatus.INFO,"Clicking on element " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.click();
            logger.log(LogStatus.PASS,"Able to click on element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName + " " + e);
            getScreenshot(driver,logger,elementName);
        }
    }//end of click method

    //method to enter on an element
    public static void input(WebDriver driver, ExtentTest logger, String locator, int index, String userInput,String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        System.out.println("Entering value " + userInput + " on element " + elementName);
        logger.log(LogStatus.INFO,"Entering value " + userInput + " on element " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.clear();
            element.sendKeys(userInput);
            logger.log(LogStatus.PASS,"Able to enter value " + userInput + " on element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to enter on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to enter on element " + elementName + " " + e);
            getScreenshot(driver,logger,elementName);
        }
    }//end of sendkeys method

    //method to hover to an element
    public static void mouseHover(WebDriver driver, ExtentTest logger, String locator, int index,String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        Actions mouse = new Actions(driver);
        System.out.println("Hovering to an element " + elementName);
        logger.log(LogStatus.INFO,"Hovering to an element " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            mouse.moveToElement(element).perform();
            logger.log(LogStatus.PASS,"Able to hover over element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to hover on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to hover on element " + elementName + " " + e);
            getScreenshot(driver,logger,elementName);
        }
    }//end of mouse hover

    //method to hover to an element
    public static void clickWithMouse(WebDriver driver, String locator, int index,String elementName){
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        Actions mouse = new Actions(driver);
        System.out.println("Clicking to an element " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            mouse.moveToElement(element).click().build().perform();
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
        }
    }//end of mouse hover


    //method for get Title
    public static void getTitle(WebDriver driver,ExtentTest logger,String expectedTitle) throws IOException {
        //verify the title of the page matches with your expected
        String actualtitle = driver.getTitle();
        if(actualtitle.equals(expectedTitle)){
            System.out.println("Title matches");
            logger.log(LogStatus.PASS,"Title matches - " + expectedTitle);
        } else {
            System.out.println("Title doesn't match - " + actualtitle);
            logger.log(LogStatus.FAIL,"Title doesn't match - " + actualtitle);
            getScreenshot(driver,logger,"Title");
        }//end of if else

    }//end of gettitle method

    public static String getText(WebDriver driver, ExtentTest logger,String locator, int index, String elementName) throws IOException {
        String result = null;
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        System.out.println("capturing text element from " + elementName);
        logger.log(LogStatus.INFO,"capturing text element from " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            result = element.getText();
            logger.log(LogStatus.PASS,"Able to capture text from element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to locate element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to locate element " + elementName + " " + e);
            getScreenshot(driver,logger,elementName);
        }

        return result;
    }

    //select by text
    public static void selectByText(WebDriver driver, ExtentTest logger,String locator, int index, String value, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        System.out.println("Selecting " + value + " from " + elementName);
        logger.log(LogStatus.INFO,"Selecting " + value + " from " + elementName);
        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            Select select = new Select(element);
            select.selectByVisibleText(value);
            logger.log(LogStatus.PASS,"Able to select " + value + " for element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to select from element " + elementName + " " + e);
            logger.log(LogStatus.FAIL,"Unable to select from element " + elementName + " " + e);
            getScreenshot(driver,logger,elementName);
        }
    }//end of select by text method

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static String getDateTime() {
        SimpleDateFormat sdfDateTime;
        String strDateTime;
        sdfDateTime = new SimpleDateFormat("yyyyMMdd'_'HHmmss'_'SSS");
        Date now = new Date();
        strDateTime = sdfDateTime.format(now);
        return strDateTime;
    }

}//end of class
