package com.ui.automation.framework.pages;

import org.openqa.selenium.By;
import com.ui.automation.framework.data.paymentData;
import com.ui.automation.framework.utils.framework;

public class paymentPage {
    framework fw;
    private final By cardNameField = By.xpath("//input[@name='name_on_card']");
    private final By cardNumberField = By.xpath("//input[@name='card_number']");
    private final By cardCvcField = By.xpath("//input[@name='cvc']");
    private final By cardExpiryMonthField = By.xpath("//input[@name='expiry_month']");
    private final By cardExpiryYearField = By.xpath("//input[@name='expiry_year']");
    private final By payButtonField = By.xpath("//button[@id='submit']");
    private final By orderPlacedMsg = By.xpath("//h2[@data-qa='order-placed']");
    private final By continueAfterPaymentButton =  By.xpath("//a[@data-qa='continue-button']");
    private final By downloadInvoiceButton =  By.xpath("//a[@class='btn btn-default check_out']");

    public paymentPage(framework fw) {
        this.fw = fw;
    }
     public void fillPayment(paymentData data) {
         fw.sendKeys(cardNameField,data.cardName,5);
         fw.sendNumbers(cardNumberField,data.cardNumber,5);
         fw.sendNumbers(cardCvcField,data.cardCvc,5);
         fw.sendNumbers(cardExpiryMonthField, data.cardExpiryMonth ,5);
         fw.sendNumbers(cardExpiryYearField,data.cardExpiryYear,5);
     }

     public void downloadInvoice() {
        fw.click(downloadInvoiceButton,5);
     }

     public void pay(){
        fw.click(payButtonField,5);
        fw.waitForVisibility(orderPlacedMsg,5);
     }

     public  void continueAfterPayment(){
        fw.click(continueAfterPaymentButton,5);
        fw.waitForInvisibility(continueAfterPaymentButton,5);
     }

     public boolean orderPlacedSuccessfully(){
         return  fw.waitForVisibility(orderPlacedMsg,5);
    }

}
