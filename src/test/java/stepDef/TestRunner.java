package stepDef;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/java/features",
				glue= {"stepDef"},
                plugin= {"pretty",
						 "json:target/json/cucumber.json"})
public class TestRunner {

}
