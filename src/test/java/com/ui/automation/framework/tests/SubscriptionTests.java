package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubscriptionTests extends BaseTest {

    @Test
    public void shouldSubscribeFromHomePage() {
        home.scrollToBottom();
        home.subscribe(userData);
        Assert.assertTrue(home.isSubscribtionMadeSuccessfully(), "subscription failed");
    }

    @Test
    public void shouldSubscribeFromCartPage() {
        home.goToCart();
        home.scrollToBottom();
        cartPage.subscribe(userData);
        Assert.assertTrue(cartPage.isAccountSubscribedSuccessfully(), "subscription failed");
    }
}