package com.mostafa.automation.flows;

import com.mostafa.automation.data.paymentData;
import automation.pages.*;
import com.mostafa.automation.data.userData;
import com.mostafa.automation.pages.*;

public class flow {
    private homePage home;
    private getStartedPage getStarted;
    private signUpPage signUp;
    private cartPage cartPage;
    private paymentPage paymentPage;
    private CheckOutPage checkOutPage;

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
        checkOutPage.writeComment("product is  perfect");
        checkOutPage.PlaceOrder();
        paymentPage.fillPayment(data);
        paymentPage.pay();
    }


}