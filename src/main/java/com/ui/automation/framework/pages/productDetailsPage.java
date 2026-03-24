package com.ui.automation.framework.pages;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import com.ui.automation.framework.data.productDetailsData;
import com.ui.automation.framework.data.reviewData;
import com.ui.automation.framework.utils.framework;

public class productDetailsPage {
     framework fw;

    public productDetailsPage(framework fw) {
        this.fw = fw;
    }

    private final By productName = By.xpath("//div[@class='product-information']/h2");
    private final By productCategory = By.xpath("//div[@class='product-information']/p[1]");
    private final By productPrice = By.xpath("//div[@class='product-information']//span/span");
    private final By productAvailability = By.xpath("//b[contains(text(),'Availability:')]/parent::p");
    private final By productCondition = By.xpath("//b[contains(text(),'Condition:')]/parent::p");
    private final By productBrand = By.xpath("//b[contains(text(),'Brand:')]/parent::p");
    private final By productQuantity = By.xpath("//input[@type='number']");
    private final By addToCartButton = By.xpath("//button[@type='button']");
    private final By viewCartButton = By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");

    private final By reviewerNameField = By.xpath("//input[@id='name']");
    private final By reviewerEmailField = By.xpath("//input[@id='email']");
    private final By reviewField= By.xpath("//textarea");
    private final By submitButton = By.xpath("//button[@id='button-review']");
    private final By reviewSuccessMsg = By.xpath("//div[@class='alert-success alert']//span");

    public void setProductQuantity(int quantity){

        fw.sendNumbers(productQuantity,quantity,5);
    }

    public void addToCart(){
        fw.click(addToCartButton,5);
    }

    public boolean checkAllProductDetails(productDetailsData data) {

        SoftAssert soft = new SoftAssert();

        soft.assertEquals(fw.getText(productName, 5).trim(), data.expectedName.trim());
        soft.assertEquals(fw.getText(productCategory, 5).trim(), data.expectedCategory.trim());
        soft.assertEquals(fw.getText(productPrice, 5).trim(), data.expectedPrice.trim());
        soft.assertTrue(fw.getText(productAvailability, 5).trim().contains(data.expectedAvailability.trim()));
        soft.assertTrue(fw.getText(productCondition, 5).trim().contains(data.expectedCondition.trim()));
        soft.assertTrue(fw.getText(productBrand, 5).trim().contains(data.expectedBrand.trim()));

            soft.assertAll();
            return true;
    }

    public void writeReview(reviewData data) {
        fw.sendKeys(reviewerNameField, data.reviewerName, 5);
        fw.sendKeys(reviewerEmailField, data.reviewerEmail, 5);
        fw.sendKeys(reviewField, data.reviewText, 5);
        fw.click(submitButton,5);
        fw.waitForVisibility(reviewSuccessMsg,5);
    }

    public boolean isReviewMadeSuccessfully() {
        return fw.isElementDisplayed(reviewSuccessMsg);
    }

    public boolean areWeInProductsDetailsPage() {
        return fw.isElementDisplayed(productName);
    }

    public  void viewCart() {
        fw.click(viewCartButton,5);
    }


}
