package com.ui.automation.framework.pages;

import org.openqa.selenium.By;
import com.ui.automation.framework.data.userData;
import com.ui.automation.framework.utils.framework;

public class signUpPage {
    framework fw;
    private final By mrTitle =By.xpath("//input[@value='Mr']");
    private final By mrsTitle =By.xpath("//input[@value='Mrs']");
    private final By signUpPasswordField = By.xpath("//input[@type='password']");
    private final By dayOfBirth = By.xpath("//select[@id='days']");
    private final By monthOfBirth = By.xpath("//select[@id='months']");
    private final By yearOfBirth = By.xpath("//select[@id='years']");
    private final By newsletterCheckbox = By.xpath("//input[@name='newsletter']");
    private final By specialOffersCheckbox = By.xpath("//input[@name='optin']");
    private final By signUpFirstNameField = By.xpath("//input[@name='first_name']");
    private final By signUpLastNameField = By.xpath("//input[@name='last_name']");
    private final By signUpCompanyField = By.xpath("//input[@name='company']");
    private final By signUpAddress1Field = By.xpath("//input[@id='address1']");
    private final By signUpAddress2Field = By.xpath("//input[@id='address2']");
    private final By signUpCountryButton = By.xpath("//select[@id='country']");
    private final By signUpStateField  = By.xpath("//input[@id='state']");
    private final By signUpCityField = By.xpath("//input[@id='city']");
    private final By signUpZipCodeField = By.xpath("//input[@id='zipcode']");
    private final By  signUpPhoneField = By.xpath("//input[@id='mobile_number']");
    private final By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private final  By accountCreatedMsg = By.xpath("//section//h2");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public signUpPage(framework fw) {
        this.fw = fw;
    }

    public void signUpForMan(userData data) {
        fw.click(mrTitle, 5);
        signUp(data);
    }
    public void signUpForWoman(userData data) {
        fw.click(mrsTitle, 5);
        signUp(data);
    }

    public void signUp(userData data )
     {
        fw.sendKeys(signUpPasswordField, data.Password ,5);
        fw.selectdropdownbycontain(dayOfBirth, String.valueOf(data.dayOfBirth),5);
        fw.selectdropdownbycontain(monthOfBirth, String.valueOf(data.monthOfBirth),5);
        fw.selectdropdownbycontain(yearOfBirth, String.valueOf(data.yearOfBirth),5);
        fw.click(newsletterCheckbox,5);
        fw.click(specialOffersCheckbox,5);
        fw.sendKeys(signUpFirstNameField , data.signUpFirstName,5);
        fw.sendKeys(signUpLastNameField, data.signUpLastName,5);
        fw.sendKeys(signUpCompanyField , data.signUpCompany ,5);
        fw.sendKeys(signUpAddress1Field , data.signUpAddress1 ,5);
        fw.sendKeys(signUpAddress2Field , data.signUpAddress2 ,5);
        fw.selectdropdownbycontain(signUpCountryButton,data.signUpCountry ,5);
        fw.sendKeys(signUpStateField , data.signUpState ,5);
        fw.sendKeys(signUpCityField,data.signUpCity , 5);
        fw.sendNumbers(signUpZipCodeField,data.signUpZipCode,5);
        fw.sendKeys(signUpPhoneField,data.signUpPhone,5);
        fw.click(createAccountButton,5);
        fw.clickUntilGone(continueButton,5);
    }

    public boolean isAccountCreatedMsgDisplayed() {
        return fw.isElementDisplayed(accountCreatedMsg);
    }

}
