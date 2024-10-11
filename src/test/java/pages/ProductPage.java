package pages;

import io.cucumber.datatable.DataTable;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.LogUtils;

import java.util.*;

public class ProductPage {


    By inputSearchItem = By.xpath("//input[@class='search-keyword']");
    By divSearchItemResult = By.xpath("//div[@class='products']/div");
    By divSearchItemNameResultCheck = By.xpath("//div[@class='products']/div/h4");
    By divitemPrice = By.xpath("//div[@class='products']//p");
    By inputQuality = By.xpath("//input[@class='quantity']");
    By buttonAddToCart = By.xpath("//div[@class='product-action']");
    By divNoResultSearch = By.xpath("//div[@class='no-results']/h2");
    By itemQualityinCart = By.xpath("//td[text()='Items']/parent::tr");
    By itemPriceinCart = By.xpath("//td[text()='Price']/parent::tr");
    By cartItem = By.xpath("//li[@class='cart-item']");
    By cartItemProductName = By.xpath("//li[@class='cart-item']//p[@class='product-name']");
    By cartItemProductPrice = By.xpath("//li[@class='cart-item']//p[@class='product-price']");
    By cartItemProductAmount= By.xpath("//li[@class='cart-item']//p[@class='amount']");
    By buttonProceedToCheckout= By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    By cartIcon = By.xpath("//a[@class='cart-icon']");
    By btnSearch = By.xpath("//button[@class='search-button']");


    public List <WebElement> searchItem(String searchItem){
        WebUI.clearText(inputSearchItem);
        WebUI.setText(inputSearchItem, searchItem);
        WebUI.sleep(1);
//        WebUI.clickElement(btnSearch);
        List <WebElement> searchItemResults = WebUI.getWebElements(divSearchItemResult);
        LogUtils.info("Number of items in search result: "+searchItemResults.size());
        if (WebUI.verifyElementExists(divSearchItemNameResultCheck)) {
            LogUtils.info("Show information of list search Item result");
            for (int i = 0; i < searchItemResults.size(); i++) {
                String itemNameActual = WebUI.getWebElements(divSearchItemNameResultCheck).get(i).getText();
                LogUtils.info("itemNameActual: " + itemNameActual);
                WebUI.verifyContainsIgnoreCase(itemNameActual, searchItem);
            }
            return searchItemResults;
        } else {
            WebUI.verifyElementTextEquals(divNoResultSearch, "Sorry, no products matched your search!");
//            String noResultMessage = WebUI.getTextElement(divNoResultSearch);
//            LogUtils.info(noResultMessage);
//            if(searchItem.contains("not exist")) {
//                AssertUtil.assertEquals(noResultMessage, "Sorry, no products matched your search!", "No Result message is not match");
//            } else {
//                AssertUtil.assertFalse(true, "search function is not working!");
//            }
            return null;
        }
    }

    public void addToCards(DataTable dataTable){
        List<Map<String, String>> dataList = dataTable.asMaps();
        int totalAmountCalculation = 0;
        HashMap<String, Integer> itemInCart = new HashMap<String, Integer>();

        for (Map<String, String> data : dataList) {
            String searchItem = data.get("search item");
            String quality = data.get("quality");
            String itemPriceExpected = data.get("price");
            String itemNameExpected = data.get("item");


            List<String> itemNameExpectedList = Arrays.stream(itemNameExpected.trim().split(", ")).toList();
            LogUtils.info("itemNameExpectedList: " + itemNameExpectedList);
            List<String> itemPriceExpectedList = Arrays.stream(itemPriceExpected.trim().split(", ")).toList();
            LogUtils.info("itemPriceExpectedList: " + itemPriceExpectedList);
            List<String> qualityList = Arrays.stream(quality.trim().split(", ")).toList();
            LogUtils.info("qualityList: " + qualityList);
            List<WebElement> searchItemResults = searchItem(searchItem);
            System.out.println("searchItemResults: " + searchItemResults);
                for (int j = 0; j < itemNameExpectedList.size(); j++) {

                    if (searchItemResults != null) {
                        for (int i = 0; i < searchItemResults.size(); i++) {
                            String itemNameActual = WebUI.getWebElements(divSearchItemNameResultCheck).get(i).getText();
                            if(itemNameActual.equals(itemNameExpectedList.get(j))){
                            LogUtils.info("itemNameActual: " + itemNameActual);
                            LogUtils.info("i: " + i);
                            LogUtils.info("j: " + j);
                            LogUtils.info("itemNameExpectedList.get(j): " + itemNameExpectedList.get(j));
                            WebUI.verifyContains(itemNameActual, itemNameExpectedList.get(j));

                            String itemPriceActual = WebUI.getWebElements(divitemPrice).get(i).getText();
                            LogUtils.info("itemPriceActual: " + itemPriceActual);
                            WebUI.verifyEquals(itemPriceActual, itemPriceExpectedList.get(j));

                            String defaultQuality = WebUI.getWebElements(inputQuality).get(i).getAttribute("value");
                            WebUI.verifyEquals(Integer.parseInt(defaultQuality), 1, "Default is not 1");

                            if (!quality.equals(defaultQuality)) {
                                WebUI.clearAndFillText(inputQuality, qualityList.get(j));
                            }
                            //Verify quality of item and price before add to cart
                            System.out.println("86: " + WebUI.getValueOnTableByRowColumn(1, 3));
                            System.out.println("87: " + WebUI.getValueOnTableByRowColumn(2, 3));
                            int qualityInCartBeforeAddToCartInt = Integer.parseInt(WebUI.getValueOnTableByRowColumn(1, 3));
                            int PriceInCartBeforeAddToCartInt = Integer.parseInt(WebUI.getValueOnTableByRowColumn(2, 3));
                            //Add item to cart
                            System.out.println("99: " + i);
                            WebUI.getWebElements(buttonAddToCart).get(i).click();

                            //Verify quality of item and price after add to cart
                            int qualityInCartAfterAddToCart = Integer.parseInt(WebUI.getValueOnTableByRowColumn(1, 3));
                            int qualityInCartAfterAddToCartExpected;
                            // Check item have already add to cart before or not.
                            System.out.println("109: " + itemInCart);
                            System.out.println("itemInCart.keySet().contains(itemNameActual): " + itemInCart.keySet().contains(itemNameActual));
                            if (!itemInCart.keySet().contains(itemNameActual)) {
                                itemInCart.put(itemNameActual, Integer.parseInt(qualityList.get(j)));
                                qualityInCartAfterAddToCartExpected = 1 + qualityInCartBeforeAddToCartInt;
                                System.out.println("113: " + itemInCart);
                            } else {
                                itemInCart.put(itemNameActual, itemInCart.get(itemNameActual) + Integer.parseInt(qualityList.get(j)));
                                qualityInCartAfterAddToCartExpected = qualityInCartBeforeAddToCartInt;
                                System.out.println("116: " + itemInCart);
                                System.out.println("117: " + qualityInCartAfterAddToCartExpected);
                            }
                            WebUI.verifyEquals(qualityInCartAfterAddToCart, qualityInCartAfterAddToCartExpected, "Quality " + itemNameActual + " in cart is not match");

                            int PriceInCartAfterAddToCart = Integer.parseInt(WebUI.getValueOnTableByRowColumn(2, 3));
                            int PriceInCartAfterAddToCartExpected = Integer.parseInt(qualityList.get(j)) * Integer.parseInt(itemPriceActual) + PriceInCartBeforeAddToCartInt;
                            WebUI.verifyEquals(PriceInCartAfterAddToCart, PriceInCartAfterAddToCartExpected, "Quality Item in cart is not match");

                        }
                    }
                    } else {
                        return;
                    }
                }
                LogUtils.info("itemInCart: " + itemInCart);
                verifyProceedToCheckoutPopupPage(itemNameExpectedList, itemPriceExpectedList, qualityList);

        }
    }

    public void verifyProceedToCheckoutPopupPage(List<String> itemNameExpectedList, List<String> itemPriceExpectedList, List<String> qualityList){
        WebUI.clickElement(cartIcon);
        WebUI.sleep(2);
        WebUI.waitForElementVisible(buttonProceedToCheckout,2);
        //Verify item in proceed to checkout popup page
        for(int j = 0; j < itemNameExpectedList.size() ; j++) {
            List<WebElement> listCartItem = WebUI.getWebElements(cartItem);
            boolean existedItem = false;
            for(int i = 0; i < listCartItem.size(); i++){
                LogUtils.info("Product Name" + WebUI.getWebElements(cartItemProductName).get(i).getText());
                LogUtils.info("itemNameExpected: " + itemNameExpectedList.get(j));
                if(WebUI.getWebElements(cartItemProductName).get(i).getText().equals(itemNameExpectedList.get(j))){
                    WebUI.verifyEquals(WebUI.getWebElements(cartItemProductPrice).get(i).getText(), itemPriceExpectedList.get(j), "Price in proceed to checkout not match");
                    WebUI.verifyEquals(Integer.parseInt(WebUI.getWebElements(cartItemProductAmount).get(i).getText()), Integer.parseInt(itemPriceExpectedList.get(j))*Integer.parseInt(qualityList.get(j)), "Price in proceed to checkout not match");
                    existedItem = true;
                    break;
                }
            }
            WebUI.verifyTrue(existedItem, itemNameExpectedList.get(j) + " is not found in proceed to checkout");
        }
        // Click button cartIcon to turn off popup
        WebUI.clickElement(cartIcon);
    }

    public void goToPlaceOrderPage(){
        WebUI.clickElement(cartIcon);
        WebUI.clickElement(buttonProceedToCheckout);
        WebUI.sleep(1);
    }

    public boolean checkDuplicateList(List<String> list){
        Set<String> set = new HashSet<>();
        for(String item: list){
            if(!set.add(item)){
                return true;
            }
        }
        return false;
    }

}
