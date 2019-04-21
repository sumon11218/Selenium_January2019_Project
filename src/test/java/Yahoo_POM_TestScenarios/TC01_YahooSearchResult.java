package Yahoo_POM_TestScenarios;

import PageObjectClasses.Yahoo_Page_Object.Yahoo_Base_Class;
import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods_Loggers_POM;
import org.testng.annotations.Test;

import java.io.IOException;

import static PageObjectClasses.Yahoo_Page_Object.Yahoo_Base_Class.yahoo_homepage;
import static PageObjectClasses.Yahoo_Page_Object.Yahoo_Base_Class.yahoo_searchResultPage;

public class TC01_YahooSearchResult extends Abstract_Class {


    @Test
    public void YahooSearch() throws IOException, InterruptedException {

        //navigating to yahoo homepage
        driver.navigate().to("https://www.yahoo.com");
        //enter a keyword on search field
        yahoo_homepage().searchKey("Cars");
        //click on search icon
        yahoo_homepage().clickOnSearchIcon();
        //scroll down few times on search page
        yahoo_searchResultPage().scrollDown("10000");
        //capture the search number
        yahoo_searchResultPage().searchNumber();
    }



}
