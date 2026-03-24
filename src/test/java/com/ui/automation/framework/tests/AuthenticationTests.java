package com.ui.automation.framework.tests;

import com.ui.automation.framework.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Authentication")
public class AuthenticationTests extends BaseTest {

    @Test
    @Story("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user can sign up successfully.")
    public void shouldSignUpSuccessfully() {
        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        home.waitForUserLogoVisibility();
        Assert.assertTrue(signUp.isAccountCreatedMsgDisplayed());
    }

    @Test
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldLoginWithValidCredentials() {
        home.goToSignupLogin();
        flow.fullSignInFlow(userData);
       home.waitForUserLogoVisibility();
        Assert.assertTrue(home.isAccountCreatedSuccessfully());
    }

    @Test
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldNotLoginWithInvalidCredentials() {
        home.goToSignupLogin();
        getStarted.invalidLogin(invalidLoginData);
        Assert.assertFalse(home.isAccountCreatedSuccessfully());
    }

    @Test
    @Story("Logout")
    public void shouldLogoutSuccessfully() {
        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        home.logout();
        Assert.assertFalse(home.isUserLogoDisplayed());
    }

    @Test
    @Story("Signup Validation")
    public void shouldNotSignUpWithExistingEmail() {
        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        home.logout();
        home.goToSignupLogin();
        getStarted.startToSignUp(userData);
        Assert.assertTrue(getStarted.isAccountAlreadyExists());
    }
}