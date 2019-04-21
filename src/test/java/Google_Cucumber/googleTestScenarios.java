package Google_Cucumber;

import Reusable_Classes.Reusable_Methods;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class googleTestScenarios extends Reusable_Methods {
    WebDriver driver;
    @Given("^I navigate to google.com$")
    public void openBrowser() throws IOException {
        driver = navigate(driver,"https://www.google.com");
    }

    @When("^I verify my Google Home title states as 'GOOGLE'$")
    public void verifyHomeTitle(){
        getTitle(driver,"GOOGLE");
    }

    @Then("^I enter a keyword in my search field$")
    public void enterKeyword(){
        input(driver,"//*[@name='q']",0,"Cars","Search Field");
    }

    @And("^I click on Search Icon$")
    public void clickOnSearchIcon(){
        click(driver,"//*[@name='btnK']",0,"Search Icon");
    }

    @When("^I Verify the Google Search Title as 'Google Search'$")
    public void VerifySearchPageTitle(){
        getTitle(driver,"Google Search");
    }

    @Then("^I capture the search result number$")
    public void CaptureSearchResult(){
        String searchResult = getText(driver,"//*[@id='resultStats']",0,"Search Result Text");
        String[] arraySearch = searchResult.split(" ");
        System.out.println("Search Number is " + arraySearch[1]);
    }


}
