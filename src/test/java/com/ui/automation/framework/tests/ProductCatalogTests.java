package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Product Catalog")
public class ProductCatalogTests extends BaseTest {

    @Test
    @Story("Navigation")
    public void shouldNavigateToTestCasesPage() {
        home.goToTestCasesPage();
        Assert.assertTrue(fw.getCurrentURL().contains("test_cases"));
    }

    @Test
    @Story("Product Details")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user can open product details and see correct product info.")
    public void shouldOpenProductDetailsAndDisplayCorrectProductInfo() {
        home.goToproductsPage();
        productPage.viewProduct1();
        Assert.assertTrue(productDetailsPage.checkAllProductDetails(productDetailsData));
    }

    @Test
    @Story("Search")
    public void shouldSearchProductAndOpenCorrectDetails() {
        home.goToproductsPage();
        productPage.search(productDetailsData);
        productPage.viewProduct1();
        Assert.assertTrue(productDetailsPage.checkAllProductDetails(productDetailsData));
    }

    @Test
    @Story("Categories")
    public void shouldViewProductsByCategory() {
        home.goToproductsPage();
        productPage.WomenTops();
        Assert.assertTrue(productPage.getProductTitle().contains("WOMEN"));
    }

    @Test
    @Story("Brands")
    public void shouldViewProductsByBrand() {
        home.goToproductsPage();
        productPage.chooseBIBA();
        Assert.assertTrue(productPage.getProductTitle().contains("BIBA"));
    }
}