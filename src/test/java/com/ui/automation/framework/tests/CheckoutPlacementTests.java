package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Checkout")
public class CheckoutPlacementTests extends BaseTest {

    @Test
    @Story("Register During Checkout")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify user can register during checkout and successfully place order.")
    public void shouldPlaceOrderWhenRegisteringDuringCheckout() {

        home.goToproductsPage();
        productPage.addProduct2ToCart( );
        productPage.viewCart();
        cartPage.checkout();

        cartPage.register_signin_BeforeCheckout();
        flow.fullSignUpFlow(userData);

        home.goToCart();
        flow.checkOutAndPaymentFlow(paymentData);

        Assert.assertTrue(paymentPage.orderPlacedSuccessfully());
    }

    @Test
    @Story("Registered User Checkout")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldPlaceOrderWhenRegisteredBeforeCheckout() {

        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);

        home.goToproductsPage();
        productPage.addProduct2ToCart( );
        productPage.viewCart();

        flow.checkOutAndPaymentFlow(paymentData);

        Assert.assertTrue(paymentPage.orderPlacedSuccessfully());
    }

    @Test
    @Story("Logged-in User Checkout")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldPlaceOrderWhenLoggedInBeforeCheckout() {

        home.goToSignupLogin();
        flow.fullSignInFlow(userData);

        home.goToproductsPage();
        productPage.addProduct1ToCart();
        productPage.viewCart();

        flow.checkOutAndPaymentFlow(paymentData);

        Assert.assertTrue(paymentPage.orderPlacedSuccessfully());
    }
}
