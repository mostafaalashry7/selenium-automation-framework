package com.ui.automation.framework.pages;

import org.openqa.selenium.By;
import com.ui.automation.framework.data.userData;

import com.ui.automation.framework.utils.framework;

public class cartPage {

    framework fw;

    private final By subscriptionEmailField = By.xpath("//input[@type='email']");
    private final By subscriptionButton = By.xpath("//button[@id='subscribe']");
    private final By subscriptionSuccessMsg = By.xpath("//div[@id='success-subscribe']");

    private final By checkoutButton = By.xpath("//a[@class='btn btn-default check_out']");
    private final By removeProduct1Button = By.xpath("(//a[@class='cart_quantity_delete'])[1]");
    private final By product1Photo = By.xpath("//img[@src='get_product_picture/1']");
    private final By productQuantityField = By.xpath("//tr[@id='product-1']//button[@class='disabled']");
    private final By register_signin = By.xpath("(//u)[1]");

    public cartPage(framework fw) {
        this.fw = fw;
    }

    public void subscribe(userData data) {
        fw.sendKeys(subscriptionEmailField, data.Email, 5);
        fw.click(subscriptionButton, 5);
        fw.waitForVisibility(subscriptionSuccessMsg, 5);
    }

    public void checkout() {
        fw.click(checkoutButton, 5);
    }

    public void removeProduct1( ) {
        fw.click(removeProduct1Button, 5);
        fw.waitForInvisibility(removeProduct1Button,5);
    }

    public void register_signin_BeforeCheckout() {
        fw.click(register_signin, 5);
        fw.waitForInvisibility(register_signin, 5);
    }

    public int getProductQuantity() {
        int actualQuantity = fw.getInt(productQuantityField, 5);
        return actualQuantity;
    }

    public boolean isAccountSubscribedSuccessfully() {
        return fw.isElementDisplayed(subscriptionSuccessMsg);
    }

    public boolean isProduct1Displayed() {
        return fw.isElementDisplayed(product1Photo);
    }

}
