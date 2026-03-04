package com.mostafa.automation.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import com.mostafa.automation.data.productDetailsData;
import com.mostafa.automation.utils.framework;

public class productsPage {
    framework fw;

    public final  By viewProduct1 = By.xpath("//a[@href='/product_details/1']");
    //a[@data-product-id='2'  and not(ancestor::div[contains(@class,'product-overlay')])]
    public final By addProduct1 = By.xpath("//a[@data-product-id='1'] ");
    public final By addProduct2 = By.xpath("//a[@data-product-id='2'] ");

    private final By viewCartButton = By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");
    private final By continueShoppingButton = By.xpath("//div[@class='modal-footer']");


    private final By searchField = By.xpath("//input[@type='text']");
    private final By searchButton = By.xpath("//button[@id='submit_search']");

    private final By womenButton = By.xpath("//a[@href='#Women']");
    private final By womenTops = By.xpath("//a[@href='/category_products/2']");
    private final By menButton = By.xpath("//a[@href='#Men']");
    private final By menJeans = By.xpath("//a[@href='/category_products/6']");
    private final By POLOButton = By.xpath("//a[@href='/brand_products/Polo']");
    private final By BIBAButton = By.xpath("//a[@href='/brand_products/Biba']");

    private final By productsTitle = By.xpath("//h2[@class='title text-center']");
    private final By categoryTitle = By.xpath("(//h2)[1]");
    private final By brandsTitle = By.xpath("(//h2)[2]");

    public productsPage(framework fw) {
        this.fw = fw;
    }

public void search(productDetailsData data) {
    fw.sendKeys(searchField, data.expectedName , 5);
    fw.click(searchButton,5);
}
public void addToCart(WebDriver driver,By product) {
        fw.smartClick( driver, product);
}
public void viewProduct(By product) {
    fw.clickUntilGone(product,5);
    }
public void continueShopping() {
    fw.click( continueShoppingButton,5);
}

public void viewCart() {
        fw.click(viewCartButton,5);
}

    public void WomenTops() {
        fw.click(womenButton,5);
        fw.click(womenTops,5);
       // fw.waitForInvisibility(womenTops,5);
    }
public void menJeans() {
    fw.click(menButton,5);
    fw.click(menJeans,5);
    fw.waitForInvisibility(menJeans,5);
}
public void choosePOLO() {
    fw.click(POLOButton,5);
}
public void chooseBIBA() {
    fw.click(BIBAButton,5);
}

    public boolean isCategoriesTitleDisplayed() {
        return fw.isElementDisplayed(categoryTitle);

    }

    public boolean isProductsTitleDisplayed() {
        return fw.isElementDisplayed(productsTitle);
    }

    public String getProductTitle() {
       return fw.getText(productsTitle,5);
    }

}
