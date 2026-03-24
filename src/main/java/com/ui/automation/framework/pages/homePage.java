package com.ui.automation.framework.pages;

import org.openqa.selenium.By;
import com.ui.automation.framework.data.userData;

import com.ui.automation.framework.utils.framework;

public class homePage {
framework fw;


    private final By homeHeader = By.xpath("(//div[@class='row'])[1]");
    private final By homeFooter = By.xpath("//div[@class='footer-bottom']");
    private final By goUpButton = By.xpath("//a[@id='scrollUp']");

    private final By signIn_signUpButton  = By.xpath("//a[@href='/login']");
    private final By userLogo = By.xpath("//a[contains(., 'Logged in as')]");

    private final By deleteAccountButton = By.xpath("//a[@href='/delete_account']");
    private final By accountDeletedMsg = By.xpath("//h2[@data-qa='account-deleted']");
    private final By continueAfterDeleteButton = By.xpath("//a[@data-qa='continue-button']");

    private final By cartButton = By.xpath("//header//a[@href='/view_cart']");
    private final By testCasesButton = By.xpath("//ul//a[@href='/test_cases']");
    private final By productPageButton = By.xpath("//a[@href='/products']");
    private final By logoutButton = By.xpath("//a[@href='/logout']");

    private final By upperTarget = By.xpath("//section[@id='slider']//div[contains(@class,'active')]");
    private final By bottomTarget = By.xpath("//div[@class='single-widget']//h2");

    private final By recommendedItemsText = By.xpath("//div[@class='recommended_items']//h2[@class='title text-center']");
    private final By recommendedProduct = By.xpath("//div[@class='recommended_items']//a[@data-product-id='5']");
    private final By productPhoto = By.xpath("//img[@src='get_product_picture/5']");


    private final By subscriptionEmailField = By.xpath("//input[@id='susbscribe_email']");
    private final By subscriptionButton = By.xpath("//button[@id='subscribe']");
    private final By subscriptionSuccessMsg =  By.xpath("//div[@id='success-subscribe']");

    private final By viewCartButton = By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");


    public homePage(framework fw) {
        this.fw = fw;
    }

    public void scrollToBottom() {
        fw.scrollToBottom();
        fw.getwait(1);}

    public void goUpByButton() {
        fw.click(goUpButton,5);
        fw.waitForVisibility(upperTarget,5);}

    public void scrollToTop() {
        fw.scrollToTop();
    }

    public void goToSignupLogin(){
        fw.click(signIn_signUpButton,5);
    fw.waitForUrlToContain("login",5);}

    public void logout() {
    fw.clickUntilGone(logoutButton, 20);
    }

  public void goToTestCasesPage() {fw.click(testCasesButton, 5);
      fw.click(testCasesButton, 5);
      fw.waitForUrlToContain("test_cases",5);}

    public void goToproductsPage(){
      fw.click(productPageButton,5);
      fw.click(productPageButton,5);
    fw.waitForUrlToContain("products",5);}

  public void subscribe( userData data) {
    fw.sendKeys(subscriptionEmailField, data.Email, 5);
    fw.click(subscriptionButton, 5);
    fw.waitForVisibility(subscriptionSuccessMsg,5);
   }

    public void goToCart() {fw.click(cartButton, 5);
    fw.waitForUrlToContain("cart",5);}

    public void deleteAccount() {
        fw.click(deleteAccountButton, 5);
        fw.waitForInvisibility(userLogo,5);
         }

    public void addRecommendedProductToCart() {
        fw.click( recommendedProduct ,5);
    fw.waitForVisibility(viewCartButton,5);}

    public void viewCart() {
        fw.click(viewCartButton,5);
        fw.waitForUrlToContain("cart",5);
    }

    public boolean isAccountDeletedSuccessfully() {
        return fw.isElementDisplayed(accountDeletedMsg);
    }

    public boolean isSubscribtionMadeSuccessfully() {return fw.isElementDisplayed(subscriptionSuccessMsg);}

    public boolean isHomeFooterDisplayed() {
        return fw.isElementDisplayed(homeFooter);
    }

    public boolean isRecommendedItemsDisplayed() {
        return fw.isElementDisplayed(recommendedItemsText);
    }

    public boolean isRecommendedProductDisplayed() {
        return fw.isElementDisplayed(productPhoto);
    }

    public boolean isAccountCreatedSuccessfully() {
        return fw.waitForVisibility(userLogo,5);
    }

    public boolean isBottomTargetDisplayed() {
        return fw.waitForVisibility(bottomTarget,8);
    }

    public boolean isUpperTargetDisplayed() {
        return fw.waitForVisibility(upperTarget,8);
    }

    public boolean isUserLogoDisplayed() {return fw.waitForVisibility(userLogo,5);}

    public void waitForUserLogoVisibility() {fw.waitForVisibility(userLogo,5);}


}

