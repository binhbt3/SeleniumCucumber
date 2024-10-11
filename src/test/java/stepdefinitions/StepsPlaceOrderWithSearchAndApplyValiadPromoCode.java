package stepdefinitions;

import hooks.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import keywords.WebUI;
import pages.PlaceOrderPage;
import pages.ProductPage;
import utils.LogUtils;

import java.util.List;
import java.util.Map;

public class StepsPlaceOrderWithSearchAndApplyValiadPromoCode {
    ProductPage productPage;
    PlaceOrderPage placeOrderPage;

    public StepsPlaceOrderWithSearchAndApplyValiadPromoCode(TestContext testContext) {
        productPage = testContext.getProductPage();
        placeOrderPage = testContext.getPlaceOrderPage();
    }

    @When("User search products and add to cart")
    public void userSearchProductsAndAddToCart(DataTable dataTable) {
        productPage.addToCards(dataTable);
        productPage.goToPlaceOrderPage();
        }

    @And("The user proceeds to checkout and apply promoCode")
    public void theUserProceedsToCheckoutAndApplyPromoCode(DataTable dataTable) {
        placeOrderPage.placeOrderConfirmation(dataTable);
    }

    @And("The user enter country {string}")
    public void theUserEnterCountry(String country) {
        placeOrderPage.theUserEnterCountry(country);
    }

    @And("The user agree terms conditions")
    public void theUserAgreeTermsConditions() {
        placeOrderPage.theUserAgreeTermsConditions();
    }

    @And("The user confirm order")
    public void theUserConfirmOrder() {
        placeOrderPage.completePlaceOrder();
    }

    @Then("The user should see the order confirmation message")
    public void theUserShouldSeeTheOrderConfirmationMessage() {

    }

    @Given("User is on the product page {string}")
    public void userIsOnTheProductPage(String url) {
        WebUI.openURL(url);
    }

}
