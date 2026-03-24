package com.ui.automation.framework.pages;

import org.openqa.selenium.By;
import com.ui.automation.framework.data.productDetailsData;
import com.ui.automation.framework.utils.framework;

public class productsPage {
    framework fw;
    private final By addProduct1 = By.xpath("//a[@data-product-id='1'] ");
    private final  By viewProduct1 = By.xpath("//a[@href='/product_details/1']");

    private final By addProduct2 = By.xpath("//a[@data-product-id='2'] ");

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
    fw.waitForVisibility(addProduct1,5);
}
public void addProduct1ToCart() {
        fw.smartClick( addProduct1);
        fw.waitForVisibility(viewCartButton,5);
}

    public void addProduct2ToCart() {
        fw.smartClick( addProduct2);
        fw.waitForVisibility(viewCartButton,5);
    }
public void viewProduct1() {
    fw.clickUntilGone(viewProduct1,5);
    }
public void continueShopping() {
    fw.click( continueShoppingButton,5);
    fw.waitForInvisibility(continueShoppingButton,5);
}

public  void viewCart() {
        fw.click(viewCartButton,5);
        fw.waitForInvisibility(viewCartButton,5);
}

    public void WomenTops() {
        fw.click(womenButton,5);
        fw.click(womenTops,5);

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

    public boolean isCategoriesTitleDisplayed() {return fw.isElementDisplayed(categoryTitle);}

    public boolean isProductsTitleDisplayed() {
        return fw.isElementDisplayed(productsTitle);
    }

    public String getProductTitle() {
       return fw.getText(productsTitle,5);
    }

    public boolean isViewProduct1ButtonDisplayed() {
        return fw.isElementDisplayed(viewProduct1);
    }

}
