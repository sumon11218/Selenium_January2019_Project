package Google_Cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\java\\Google_Cucumber\\GoogleTests"}
        
)
public class GoogleTestRunner {
    //this is empty
}
