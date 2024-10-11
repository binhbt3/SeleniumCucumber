package hooks;

import driver.DriverFactory;
import driver.DriverManager;
import pages.*;
//import pages.LoginCRMPage;
import utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class TestContext {

    private WebDriver driver;
    private CommonPage commonPage;
    private ProductPage productPage;
    private PlaceOrderPage placeOrderPage;

    public TestContext() {
        ThreadGuard.protect(new DriverFactory().createDriver());
        LogUtils.info("Driver in TestContext: " + getDriver());
    }

    public CommonPage getCommonPage() {
        if (commonPage == null) {
            commonPage = new CommonPage();
        }
        return commonPage;
    }

    public ProductPage getProductPage(){
        if(productPage == null){
            productPage = new ProductPage();
        }
        return productPage;
    }

    public PlaceOrderPage getPlaceOrderPage(){
        if(placeOrderPage == null){
            placeOrderPage = new PlaceOrderPage();
        }
        return placeOrderPage;
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
