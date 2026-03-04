package com.mostafa.automation.pages;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import com.mostafa.automation.data.userData;
import com.mostafa.automation.utils.framework;


public class CheckOutPage {
    framework fw;
    private final By commentField = By.xpath("//textarea");
    private final By placeOrderField = By.xpath("//a[@href='/payment']");

    private final By deliveryNameField = By.xpath("//ul[@id='address_delivery']//li[contains(@class,'address_firstname')]");
    private final By deliveryAddress1Field = By.xpath("(//ul[@id='address_delivery']//li[contains(@class,'address1')])[2]");
    private final By deliveryAddress2Field = By.xpath("(//ul[@id='address_delivery']//li[contains(@class,'address1')])[3]");
    private final By deliveryStateField = By.xpath("//ul[@id='address_delivery']//li[contains(@class,'address_city')]");
    private final By deliveryCountryField = By.xpath("//ul[@id='address_delivery']//li[contains(@class,'address_country_name')]");
    private final By deliveryPhoneField = By.xpath("//ul[@id='address_delivery']//li[contains(@class,'address_phone')]");


    private final By billingNameField      = By.xpath("//ul[@id='address_invoice']//li[contains(@class,'address_firstname')]");
    private final By billingAddress1Field  = By.xpath("(//ul[@id='address_invoice']//li[contains(@class,'address1')])[2]");
    private final By billingAddress2Field  = By.xpath("(//ul[@id='address_invoice']//li[contains(@class,'address1')])[3]");
    private final By billingStateField     = By.xpath("//ul[@id='address_invoice']//li[contains(@class,'address_city')]");
    private final By billingCountryField   = By.xpath("//ul[@id='address_invoice']//li[contains(@class,'address_country_name')]");
    private final By billingPhoneField     = By.xpath("//ul[@id='address_invoice']//li[contains(@class,'address_phone')]");



    public CheckOutPage(framework fw) {
        this.fw = fw;
    }
public void writeComment(String comment) {
        fw.scrollToCenter(commentField,5);
    fw.sendKeys(commentField,comment,5);
}

    public boolean isDeliveryAddressCorrect(userData data) {

        SoftAssert soft = new SoftAssert();


        soft.assertTrue(fw.getText(deliveryNameField, 5).trim().contains((data.signUpFirstName + " " + data.signUpLastName).trim()));
        soft.assertEquals(fw.getText(deliveryAddress1Field, 5).trim(), data.signUpAddress1.trim());
        soft.assertEquals(fw.getText(deliveryAddress2Field, 5).trim(), data.signUpAddress2.trim());
        soft.assertEquals(fw.getText(deliveryStateField, 5).trim(), (data.signUpCity +" "+ data.signUpState +" "+ data.signUpZipCode).trim());
        soft.assertEquals(fw.getText(deliveryCountryField, 5).trim(), data.signUpCountry.trim());
        soft.assertEquals(fw.getText(deliveryPhoneField, 5).trim(), data.signUpPhone.trim());

        soft.assertAll();
            return true;

    }

    public boolean isBillingAddressCorrect(userData data) {

        SoftAssert soft = new SoftAssert();

        soft.assertTrue(fw.getText(deliveryNameField, 5).trim().contains((data.signUpFirstName + " " + data.signUpLastName).trim()));
        soft.assertEquals(fw.getText(billingAddress1Field, 5).trim(), data.signUpAddress1.trim());
        soft.assertEquals(fw.getText(billingAddress2Field, 5).trim(), data.signUpAddress2.trim());
        soft.assertEquals(fw.getText(billingStateField, 5).trim(), (data.signUpCity + " "+ data.signUpState + " "+ data.signUpZipCode).trim());
        soft.assertEquals(fw.getText(billingCountryField, 5).trim(), data.signUpCountry.trim());
        soft.assertEquals(fw.getText(billingPhoneField, 5).trim(), data.signUpPhone.trim());

        soft.assertAll();
            return true;

    }

public void PlaceOrder() {
    fw.click(placeOrderField,5);
}
}

