package com.mostafa.automation.pages;

import org.openqa.selenium.By;
import com.mostafa.automation.data.userData;
import com.mostafa.automation.data.invalidLoginData;
import com.mostafa.automation.utils.framework;

public class getStartedPage {
     framework fw;

    private final By loginEmailField = By.xpath("(//input[@type='email'])[1]");
    private final By loginPasswordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");

    private final By signupNameField = By.xpath("//input[@type='text']");
    private final  By signupEmailField = By.xpath("(//input[@type='email'])[2]");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private final By continueButton = By.xpath("//a[@data-qa]");

    private final By emailExistsErrorMsg = By.xpath("//p[contains(text(),'Email Address already exist')]");

    public getStartedPage(framework fw) {
        this.fw = fw;
    }

    public  void Login(userData data) {
        fw.sendKeys(loginEmailField, data.Email, 5);
        fw.sendKeys(loginPasswordField, data.Password, 5);
        fw.clickUntilGone(loginButton, 5);

    }

    public  void invalidLogin(invalidLoginData data) {
        fw.sendKeys(loginEmailField, data.invalidLoginEmail, 5);
        fw.sendKeys(loginPasswordField, data.invalidLoginPassword, 5);
        fw.click(loginButton, 5);
    }

    public void startToSignUp(userData data) {

        fw.sendKeys(signupNameField, data.signUpFirstName, 5);
        fw.sendKeys(signupEmailField, data.Email, 5);
        fw.click(signupButton, 5);
    }


    public boolean isAccountAlreadyExists() {
        return fw.isElementDisplayed(emailExistsErrorMsg);
    }

}
