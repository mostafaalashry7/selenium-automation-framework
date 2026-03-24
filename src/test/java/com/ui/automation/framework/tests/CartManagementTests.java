package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Cart Management")
public class CartManagementTests extends BaseTest {

    @Test
    @Story("Add Products")
    public void shouldAddMultipleProductsToCart() {
        home.goToproductsPage();
        productPage.addProduct1ToCart();
        productPage.continueShopping();
        productPage.addProduct2ToCart();
        productPage.viewCart();

        Assert.assertTrue(cartPage.isProduct1Displayed());
    }

    @Test
    @Story("Quantity")
    public void shouldUpdateProductQuantityInCart() {
        home.goToproductsPage();
        productPage.viewProduct1();
        productDetailsPage.setProductQuantity(5);
        productDetailsPage.addToCart();
        productDetailsPage.viewCart();

        Assert.assertEquals(cartPage.getProductQuantity(), 5);
    }

    @Test
    @Story("Remove Product")
    public void shouldRemoveProductFromCart() {
        home.goToproductsPage();
        productPage.addProduct1ToCart();
        productPage.viewCart();
        cartPage.removeProduct1();
        Assert.assertFalse(cartPage.isProduct1Displayed());
    }

    @Test
    @Story("Cart Persistence")
    @Severity(SeverityLevel.NORMAL)
    public void shouldPersistCartAfterLogin() {
        home.goToproductsPage();
        productPage.addProduct1ToCart();
        home.goToSignupLogin();
        flow.fullSignInFlow(userData);
        home.goToCart();
        Assert.assertTrue(cartPage.isProduct1Displayed());
    }

    @Test
    @Story("Recommended Items")
    public void shouldAddRecommendedItemToCart() {
        home.scrollToBottom();
        Assert.assertTrue(home.isRecommendedItemsDisplayed(), "we cannot find the recommended items");

        home.addRecommendedProductToCart();
        home.viewCart();

        Assert.assertTrue(home.isRecommendedProductDisplayed(), "we cannot find the recommended item");
    }
}