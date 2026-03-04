package com.mostafa.automation.pages;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import com.mostafa.automation.data.productDetailsData;
import com.mostafa.automation.data.reviewData;
import com.mostafa.automation.utils.framework;

public class productDetailsPage {
     framework fw;

    public productDetailsPage(framework fw) {
        this.fw = fw;
    }

   public final By productName = By.xpath("//div[@class='product-information']/h2");
    public final By productCategory = By.xpath("//div[@class='product-information']/p[1]");
    public final By productPrice = By.xpath("//div[@class='product-information']//span/span");
    public final By productAvailability = By.xpath("//b[contains(text(),'Availability:')]/parent::p");
    public final By productCondition = By.xpath("//b[contains(text(),'Condition:')]/parent::p");
    public final By productBrand = By.xpath("//b[contains(text(),'Brand:')]/parent::p");
   public final By productQuantity = By.xpath("//input[@type='number']");
   public final By addToCartButton = By.xpath("//button[@type='button']");

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
    }

    public boolean isReviewMadeSuccessfully() {
        return fw.isElementDisplayed(reviewSuccessMsg);
    }

    public boolean areWeInProductsDetailsPage() {
        return fw.isElementDisplayed(productName);
    }


}
