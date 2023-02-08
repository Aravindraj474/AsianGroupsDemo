package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDef",
        plugin = "json:target/cucumber.json",
        tags = "@test"

)
/**
 * Unit test for simple App.
 */
public class AppTest 
{
}
