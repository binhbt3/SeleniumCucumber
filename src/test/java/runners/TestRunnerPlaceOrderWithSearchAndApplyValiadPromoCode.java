package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/PlaceOrders/PlaceOrderWithSearchAndApplyValiadPromoCode.feature",
        glue = {"stepdefinitions",
                "common",
                "hooks",
                "models"},
        plugin = {"pretty",
                "hooks.CucumberListener",
                "html:target/cucumber-reports/TestRunnerPlaceOrderWithSearchAndApplyValiadPromoCode.html",
                "json:target/cucumber-reports/TestRunnerPlaceOrderWithSearchAndApplyValiadPromoCode.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
//        tags = "@InvalidLoginMultiple"
)

@Test
public class TestRunnerPlaceOrderWithSearchAndApplyValiadPromoCode extends AbstractTestNGCucumberTests {


}
