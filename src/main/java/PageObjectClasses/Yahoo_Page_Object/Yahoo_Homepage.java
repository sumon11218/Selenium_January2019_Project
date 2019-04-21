package PageObjectClasses.Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.IOException;

public class Yahoo_Homepage extends Abstract_Class {

    ExtentTest logger;
    public Yahoo_Homepage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.logger = super.logger;
    }

    @FindBy(xpath = "//*[@name='p']")
    public static WebElement searchKeyLocator;
    @FindBy(xpath = "//*[@id='uh-search-button']")
    public static WebElement searchIconLocator;


    public Yahoo_Homepage searchKey(String userInput) throws IOException {
        Reusable_Methods_Loggers_POM.input(driver,logger,searchKeyLocator,0,userInput,"Search Field");
        return new Yahoo_Homepage(driver);
    }

    public Yahoo_Homepage clickOnSearchIcon() throws IOException {
        Reusable_Methods_Loggers_POM.click(driver,logger,searchIconLocator,0,"Search Icon");
        return new Yahoo_Homepage(driver);
    }

}
