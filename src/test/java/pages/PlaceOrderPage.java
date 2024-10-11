package pages;

import io.cucumber.datatable.DataTable;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LogUtils;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PlaceOrderPage {

    By productNameInConfirmPage = By.xpath("//tbody//p[@class='product-name']");
    By quantiryInConfirmPage = By.xpath("//tbody//p[@class='quantity']");
    By priceInConfirmPage = By.xpath("//tbody//td[4]/p");
    By totalInTable = By.xpath("//tbody//td[5]/p");
    By noOfItems = By.xpath("//b[contains(.,'No. of Items')]");
    By totalAmount = By.xpath("//span[@class='totAmt']");
    By discount = By.xpath("//span[@class='discountPerc']");
    By totalAfterDiscount = By.xpath("//span[@class='discountAmt']");
    By inputPromoCode = By.xpath("//input[@class='promoCode']");
    By buttonApply = By.xpath("//button[@class='promoBtn']");
    By applyPromoCodeMessage = By.xpath("//span[@class='promoInfo']");
    By buttonPlaceOrder = By.xpath("//button[text()='Place Order']");
    By dropDownSelectCountry = By.xpath("//select");
    By checkboxAgreeTermConditions = By.xpath("//input[@class='chkAgree']");
    By buttonProceed = By.xpath("//button[contains(text(),'Proceed')]");

    public void placeOrderConfirmation(DataTable dataTable) {
        verifyPlaceOrderConfirmation(dataTable);
    }

    public void completePlaceOrder() {
        WebUI.clickElement(buttonProceed);
    }

    public void theUserEnterCountry(String country) {
        WebUI.selectOptionByText(dropDownSelectCountry, country);
    }

    public void theUserAgreeTermsConditions() {
        WebUI.clickElement(checkboxAgreeTermConditions);
    }


    public int getNoOfItems() {
        return Integer.parseInt(WebUI.getTextElement(noOfItems));
    }

    public int getTotalAmount() {
        return Integer.parseInt(WebUI.getTextElement(totalAmount));
    }

    public int getDiscount() {
        return Integer.parseInt(WebUI.getTextElement(discount).replace("%", ""));
    }

    public String getTotalAfterDiscount() {
        return WebUI.getTextElement(totalAfterDiscount);
    }

    public void applyPromoCode(String promoCode, String applyPromoCodeMessageExpected) {
        LogUtils.info("Promo Code: " + promoCode);
        WebUI.clearAndFillText(inputPromoCode, promoCode);
        WebUI.clickElement(buttonApply);
        WebUI.verifyElementTextEquals(applyPromoCodeMessage, applyPromoCodeMessageExpected);
    }

    public void verifyPlaceOrderConfirmation(DataTable dataTable) {

        List<Map<String, String>> dataList = dataTable.asMaps();
        int totalAmountCalculation = 0;

        for (Map<String, String> data : dataList) {

            // Verify product Name, Quantiry, Price, Total in table
            List<String> itemNameExpectedList = Arrays.stream(data.get("item").trim().split(", ")).toList();
            LogUtils.info("itemNameExpectedList: " + itemNameExpectedList);
            List<String> itemPriceExpectedList = Arrays.stream(data.get("price").trim().split(", ")).toList();
            LogUtils.info("itemPriceExpectedList: " + itemPriceExpectedList);
            List<String> qualityList = Arrays.stream(data.get("quality").trim().split(", ")).toList();
            LogUtils.info("qualityList: " + qualityList);

            WebUI.waitForElementVisible(totalInTable, 1);
            List<WebElement> totalRows = WebUI.getWebElements(totalInTable);
            LogUtils.info("totalRows size: " + totalRows.size());

            for (int j = 0; j < itemNameExpectedList.size(); j++) {
                boolean check = false;
                for (int i = 1; i <= totalRows.size(); i++) {
                    System.out.println("78 : " + i);
                    System.out.println("totalAmountCalculation 79 : " + totalAmountCalculation);
                    System.out.println("itemNameExpectedList.get(j) : " + itemNameExpectedList.get(j));
                    System.out.println("WebUI.getValueOnTableByRowColumn(i, 2) : " + WebUI.getValueOnTableByRowColumn(i, 2));
                    if (WebUI.getValueOnTableByRowColumn(i, 2).equals(itemNameExpectedList.get(j))) {
                        WebUI.verifyEquals(WebUI.getValueOnTableByRowColumn(i, 3), qualityList.get(j));
                        WebUI.verifyEquals(WebUI.getValueOnTableByRowColumn(i, 4), itemPriceExpectedList.get(j));
                        WebUI.verifyEquals(Integer.parseInt(WebUI.getValueOnTableByRowColumn(i, 5)), Integer.parseInt(qualityList.get(j)) * Integer.parseInt(itemPriceExpectedList.get(j)));
                        totalAmountCalculation += Integer.parseInt(qualityList.get(j)) * Integer.parseInt(itemPriceExpectedList.get(j));
                        check = true;
                    }
                }
                WebUI.verifyTrue(check, itemNameExpectedList.get(j) + " not found in Confirm Place Order page");
            }
        }

        // Verify No. of item, Total Amount, Discount, Total Before Discount
        int totalAmountIntBeforeApplyCode = getTotalAmount();
        WebUI.verifyEquals(totalAmountIntBeforeApplyCode, totalAmountCalculation, "Total Amount is not match");
        WebUI.verifyEquals(getDiscount(), 0);
        WebUI.verifyEquals(getTotalAfterDiscount(), String.valueOf(totalAmountCalculation));

        if (dataList.get(0).get("promo code") != null) {
            applyPromoCode(dataList.get(0).get("promo code"), dataList.get(0).get("Apply code message"));
        }

        WebUI.verifyEquals(getDiscount(), Integer.parseInt(dataList.get(0).get("Discount")), "Discount is not match");
        WebUI.verifyEquals(getTotalAmount(), totalAmountCalculation, "Total Amount is not match");
        WebUI.verifyEquals(getTotalAmount(), totalAmountCalculation, "Total Amount is not match");

        if (dataList.get(0).get("Apply code message") != null) {
            if (dataList.get(0).get("Apply code message").equals("Code applied ..!")) {
                Double discount = Double.parseDouble(dataList.get(0).get("Discount"));
                String totalCaculationAfterDiscount = convertDoubleToString(totalAmountCalculation - totalAmountIntBeforeApplyCode * discount / 100);

                WebUI.verifyEquals(getTotalAfterDiscount(), totalCaculationAfterDiscount, "Total Amount is not match");
            } else {
                WebUI.verifyEquals(getTotalAfterDiscount(), String.valueOf(totalAmountCalculation), "Total Amount is not match");
            }
        }
        WebUI.clickElement(buttonPlaceOrder);
    }


    public String convertDoubleToString(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        } else {
            return String.valueOf(number);
        }
    }

}
