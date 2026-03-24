package com.ui.automation.framework.base;









import com.ui.automation.framework.data.*;

import com.ui.automation.framework.data.*;
import com.ui.automation.framework.flows.flow;
import com.ui.automation.framework.pages.*;
import com.ui.automation.framework.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.ui.automation.framework.utils.framework;
import com.ui.automation.framework.utils.readJson;
import java.io.IOException;

public class BaseTest {

    protected framework fw;

    protected userData userData;
    protected invalidLoginData invalidLoginData;
    protected paymentData paymentData;
    protected contactUsData contactUsData;
    protected productDetailsData productDetailsData;
    protected reviewData reviewData;

    protected homePage home;
    protected getStartedPage getStarted;
    protected signUpPage signUp;
    protected cartPage cartPage;
    protected paymentPage paymentPage;
    protected CheckOutPage checkOutPage;
    protected productsPage productPage;
    protected contactUsPage contactUsPage;
    protected productDetailsPage productDetailsPage;
    protected flow flow;

    public WebDriver getDriver() {
        if (fw != null ) {
            return fw.getBrowser();
        }
        return null;
    }

    @BeforeClass
    public void loadData() throws IOException {
        userData = readJson.readData("user", userData.class);
        invalidLoginData = readJson.readData("invalidLogin", invalidLoginData.class);
        paymentData = readJson.readData("payment", paymentData.class);
        contactUsData = readJson.readData("contactUs", contactUsData.class);
        productDetailsData = readJson.readData("productDetails", productDetailsData.class);
        reviewData = readJson.readData("review", reviewData.class);
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
       // userData.makeEmailChange();
        fw = new framework();
        fw.openBrowser(browser);
        fw.removeAds();

        home = new homePage(fw);
        getStarted = new getStartedPage(fw);
        signUp = new signUpPage(fw);
        cartPage = new cartPage(fw);
        paymentPage = new paymentPage(fw);
        checkOutPage = new CheckOutPage(fw);
        productPage = new productsPage(fw);
        contactUsPage = new contactUsPage(fw);
        productDetailsPage = new productDetailsPage(fw);
        flow = new flow(home, getStarted, signUp, paymentPage, checkOutPage, cartPage);

        fw.getBrowser().get("https://automationexercise.com");



    }

    @AfterMethod
    public void tearDown() {
        if (fw != null) {
            fw.closeBrowser();
        }
    }
}