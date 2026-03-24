package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactAndReviewTests extends BaseTest {

    @Test
    public void shouldSubmitContactUsFormSuccessfully() throws IOException {
        contactUsPage.fillContactUsForm(contactUsData);
        Assert.assertTrue(contactUsPage.isContactingDoneSuccessfully(), "please try again to contact us");
    }

    @Test
    public void shouldSubmitProductReviewSuccessfully() {
        home.goToproductsPage();
        productPage.viewProduct1();
        productDetailsPage.writeReview(reviewData);
        Assert.assertTrue(productDetailsPage.isReviewMadeSuccessfully(), "review failed");
    }

}