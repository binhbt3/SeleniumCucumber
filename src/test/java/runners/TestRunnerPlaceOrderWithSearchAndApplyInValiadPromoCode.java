package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/PlaceOrders/PlaceOrderWithSearchAndApplyInvalidPromoCode.feature",
        glue = {"stepdefinitions",
                "common",
                "hooks",
                "models"},
        plugin = {"pretty",
                "hooks.CucumberListener",
                "html:target/cucumber-reports/TestRunnerPlaceOrderWithSearchAndApplyInValiadPromoCode.html",
                "json:target/cucumber-reports/TestRunnerPlaceOrderWithSearchAndApplyInValiadPromoCode.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
//        tags = "@InvalidLoginMultiple"
)

@Test
public class TestRunnerPlaceOrderWithSearchAndApplyInValiadPromoCode extends AbstractTestNGCucumberTests {

}
