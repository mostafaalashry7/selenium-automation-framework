package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Feature("Checkout Validation")
public class CheckoutValidationTests extends BaseTest {

    @Test
    @Story("Address Validation")
    public void shouldDisplayCorrectAddressesAtCheckout() {

        home.goToSignupLogin();
        flow.fullSignInFlow(userData);

        home.goToproductsPage();
        productPage.addProduct1ToCart();
        productPage.viewCart();
        cartPage.checkout();

        Assert.assertTrue(checkOutPage.isDeliveryAddressCorrect(userData));
        Assert.assertTrue(checkOutPage.isBillingAddressCorrect(userData));
    }

    @Test
    @Story("Invoice Download")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user can download invoice after placing order successfully.")
    public void shouldDownloadInvoiceAfterSuccessfulOrder() {
        SoftAssert soft = new SoftAssert();

        home.goToproductsPage();
        productPage.addProduct1ToCart();
        productPage.viewCart();
        cartPage.checkout();
        cartPage.register_signin_BeforeCheckout();

        flow.fullSignUpFlow(userData);
        soft.assertTrue(home.isAccountCreatedSuccessfully(), "account creation failed");

        home.goToCart();
        flow.checkOutAndPaymentFlow(paymentData);
        soft.assertTrue(paymentPage.orderPlacedSuccessfully(), "invalid card data");

        paymentPage.downloadInvoice();
        paymentPage.continueAfterPayment();

        home.deleteAccount();
        soft.assertTrue(home.isAccountDeletedSuccessfully(), "account was not deleted");

        soft.assertAll();
    }

}
