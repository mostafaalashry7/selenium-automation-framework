package com.ui.automation.framework.flows;

import com.ui.automation.framework.data.paymentData;
import com.ui.automation.framework.pages.*;
import com.ui.automation.framework.data.userData;
import com.ui.automation.framework.pages.*;
import com.ui.automation.framework.utils.framework;

public class flow {
    private homePage home;
    private getStartedPage getStarted;
    private signUpPage signUp;
    private cartPage cartPage;
    private paymentPage paymentPage;
    private CheckOutPage checkOutPage;
    framework fw;

    public flow(homePage home, getStartedPage getStarted, signUpPage signUp , paymentPage paymentPage, CheckOutPage checkOutPage,cartPage cartPage ) {
        this.home = home;
        this.getStarted = getStarted;
        this.signUp = signUp;
        this.paymentPage = paymentPage;
        this.checkOutPage = checkOutPage;
        this.cartPage = cartPage;
    }


    public void fullSignUpFlow(userData data) {


        data.makeEmailChange();
        getStarted.startToSignUp(data);
        signUp.signUpForMan(data);

    }

    public void fullSignInFlow(userData data) {

        fullSignUpFlow(data);
        home.logout();
        getStarted.Login(data);

    }

    public void checkOutAndPaymentFlow(paymentData data) {

        cartPage.checkout();


        checkOutPage.PlaceOrder();

        paymentPage.fillPayment(data);
        paymentPage.pay();
    }


}