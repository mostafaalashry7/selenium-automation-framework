package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageUiTests extends BaseTest {

    @Test
    @Story("Scroll Navigation")
    public void shouldScrollUpUsingArrowButton() {
        home.scrollToBottom();
        Assert.assertTrue(home.isHomeFooterDisplayed());
        home.goUpByButton();
        Assert.assertTrue(home.isUpperTargetDisplayed());
    }

    @Test
    @Story("Scroll Navigation")
    public void shouldScrollUpToTopManually() {
        home.scrollToBottom();
        Assert.assertTrue(home.isBottomTargetDisplayed());
        home.scrollToTop();
        Assert.assertTrue(home.isUpperTargetDisplayed());
    }


}
