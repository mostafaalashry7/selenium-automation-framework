package com.mostafa.automation.tests;

import com.mostafa.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTests extends BaseTest {



    @Test
    public void Signup() {
        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        Assert.assertTrue(fw.isElementDisplayed(signUp.acountCreatedMsg), " please try to sign up again ");
    }

    @Test
    public void SignInWithValidData() {
        home.goToSignupLogin();
        flow.fullSignInFlow(userData);
        Assert.assertTrue(home.isAccountCreatedSuccessfully(), "wrong email or password");
    }

@Test
    public void SignInWithInvalidData() {

        home.goToSignupLogin();
        getStarted.invalidLogin(invalidLoginData);
        Assert.assertFalse(home.isAccountCreatedSuccessfully(), "you entered an invalid email or password");
    }

@Test
    public void logOut() {
        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        home.logout();
        Assert.assertTrue(fw.browser.getCurrentUrl().contains("/login"), "try again to logout");
    }

    @Test
    public void signupWithRegistredEmail() {
        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        home.logout();
        home.goToSignupLogin();
        getStarted.startToSignUp(userData);
        Assert.assertTrue(getStarted.isAccountAlreadyExists(), "email was not used before signup");
    }

    @Test
 public void contactUs(){

        contactUsPage.fillContactUsForm(contactUsData);
        Assert.assertTrue(contactUsPage.isContactingDoneSuccessfully(), "please try again to contactUs");
 }

 @Test

    public void goToTestCasesPage() {
    home.goToTestCasesPage();
    Assert.assertTrue(fw.getCurrentURL().contains("test_cases"), "wrong page");
    }

    @Test
    public void VerifyAllProductsAndProductDetailPage() {
        home.goToproductsPage();
        Assert.assertTrue(productPage.isProductsTitleDisplayed(), "wrong page");
        productPage.viewProduct(productPage.viewProduct1);
        Assert.assertTrue(productDetailsPage.areWeInProductsDetailsPage(), "wrong page");
        Assert.assertTrue(productDetailsPage.checkAllProductDetails(productDetailsData), "wrong product");

    }

    @Test

    public void searchProductAndCheckDetails() {

        home.goToproductsPage();
        productPage.search(productDetailsData);
        productPage.viewProduct(productPage.viewProduct1);
        Assert.assertTrue(productDetailsPage.checkAllProductDetails(productDetailsData), "wrong product");
    }

    @Test
    public void subscriptionInHomePage() {
        home.scrollToBottom();
        home.subscribe(userData);
       Assert.assertTrue(home.isSubscribtionMadeSuccessfully(), "subscription failed");
    }

    @Test
    public void subscriptionInCartPage() {
        home.goToCart();
        home.scrollToBottom();
        cartPage.subscribe(userData);
        Assert.assertTrue(cartPage.isAccountSubscribedSuccessfully(), "subscription failed");
    }

    @Test
    public void addProductsToCart() {
        home.goToproductsPage();
        productPage.addToCart(fw.browser, productPage.addProduct1);
        productPage.continueShopping();
        productPage.addToCart(fw.browser,productPage.addProduct2);
        productPage.viewCart();
        Assert.assertTrue(fw.isElementDisplayed(productPage.addProduct1), "item not added to cart");
        Assert.assertTrue(fw.isElementDisplayed(productPage.addProduct2), "item not added to cart");
    }

    @Test

    public void verfiyProductQuantity() {
        home.goToproductsPage();
        productPage.viewProduct(productPage.viewProduct1);
        productDetailsPage.setProductQuantity(5);
        productDetailsPage.addToCart();
        productPage.viewCart();
        Assert.assertEquals(cartPage.getProductQuantity(),5, "wrong quantity");
    }

    @Test

    public void PlaceOrderRegisterWhileCheckout(){
        home.goToproductsPage();
        productPage.addToCart(fw.browser,productPage.addProduct2);
        productPage.viewCart();
        cartPage.checkout();
        cartPage.register_signin_BeforeCheckout();
        flow.fullSignUpFlow(userData);
        Assert.assertTrue(home.isAccountCreatedSuccessfully(), "you entered an invalid email or password");

        home.goToCart();
        flow.checkOutAndPaymentFlow(paymentData);
        Assert.assertTrue(paymentPage.orderPlacedSuccessfully(),"invalid card data");

        home.deleteAccount();
        Assert.assertTrue(home.isAccountDeletedSuccessfully(),"account did not deleted");

    }

    @Test

    public void PlaceOrderRegisterBeforeCheckout(){

        home.goToSignupLogin();
        flow.fullSignUpFlow(userData);
        Assert.assertTrue(home.isAccountCreatedSuccessfully(), "you entered an invalid email or password");

        home.goToproductsPage();
        productPage.addToCart(fw.browser,productPage.addProduct2);
        productPage.viewCart();
        flow.checkOutAndPaymentFlow(paymentData);
        Assert.assertTrue(paymentPage.orderPlacedSuccessfully(),"invalid card data");

        home.deleteAccount();
        Assert.assertTrue(home.isAccountDeletedSuccessfully(),"account did not deleted");

    }

@Test
    public void PlaceOrderLoginBeforeCheckout(){
    home.goToSignupLogin();
    flow.fullSignInFlow(userData);
    Assert.assertTrue(home.isAccountCreatedSuccessfully(), "you entered an invalid email or password");

    home.goToproductsPage();
    productPage.addToCart(fw.browser,productPage.addProduct1);
    productPage.viewCart();
    flow.checkOutAndPaymentFlow(paymentData);
    Assert.assertTrue(paymentPage.orderPlacedSuccessfully(),"invalid card data");

    home.deleteAccount();
    Assert.assertTrue(home.isAccountDeletedSuccessfully(),"account did not deleted");
}

@Test
    public void RemoveProductsFromCart(){
        home.goToproductsPage();
        productPage.addToCart(fw.browser, productPage.addProduct1);
        productPage.viewCart();
        Assert.assertTrue(cartPage.isProduct1Displayed(), "product did not exist");
        cartPage.remove(cartPage.removeProduct1Button );
        Assert.assertFalse(cartPage.isProduct1Displayed(), "product didn't removed");
    }

    @Test

    public void ViewCategoryProducts(){
        home.goToproductsPage();
    Assert.assertTrue(productPage.isCategoriesTitleDisplayed(), "we cannot view the category products");
    productPage.WomenTops();
    Assert.assertTrue(productPage.getProductTitle().contains("WOMEN - TOPS"));
    productPage.menJeans();
    Assert.assertTrue(productPage.getProductTitle().contains("MEN - JEANS"));
    }

    @Test

    public void ViewCartBrandProducts(){

        home.goToproductsPage();
        productPage.chooseBIBA();
        Assert.assertTrue(productPage.getProductTitle().contains("BIBA"));
        productPage.choosePOLO();
        Assert.assertTrue(productPage.getProductTitle().contains("POLO"));

    }

    @Test

    public void SearchAndVerifyCartAfterLogin(){
       home.goToproductsPage();
       productPage.search(productDetailsData);
       productPage.addToCart(fw.browser, productPage.addProduct1);
       productPage.viewCart();
       Assert.assertTrue(cartPage.isProduct1Displayed(), "product did not exist");
       home.goToSignupLogin();
       flow.fullSignInFlow(userData);
       home.goToCart();
       Assert.assertTrue(cartPage.isProduct1Displayed(), "product did not exist");
    }

@Test

    public void reviewProduct(){
    home.goToproductsPage();
    productPage.viewProduct(productPage.viewProduct1);
    productDetailsPage.writeReview(reviewData);
    Assert.assertTrue(productDetailsPage.isReviewMadeSuccessfully(), "review faild");

}

@Test

    public void AddToCartFromRecommendedItems(){
        home.scrollToBottom();
        Assert.assertTrue(home.isRecommendedItemsDisplayed(), "we cannot find the recommended Items");
        home.addToCart(home.recommendedProduct);
        home.viewCart();
    Assert.assertTrue(home.isRecommendedProductDisplayed(), "we cannot find the recommended Item");
    }

    @Test
    public void  VerifyAddressDetailsInCheckout(){
        home.goToSignupLogin();
        flow.fullSignInFlow(userData);
        Assert.assertTrue(home.isAccountCreatedSuccessfully(), "you entered an invalid email or password");

        home.goToproductsPage();
        productPage.addToCart(fw.browser,productPage.addProduct1);
        productPage.viewCart();
        cartPage.checkout();

        Assert.assertTrue(checkOutPage.isDeliveryAddressCorrect(userData), "The delivery Address on checkout page is not matching user data");
        Assert.assertTrue(checkOutPage.isBillingAddressCorrect(userData), "The Billing Address on checkout page is not matching user data");

        home.deleteAccount();
        Assert.assertTrue(home.isAccountDeletedSuccessfully(),"account did not deleted");

    }

    @Test

    public void downloadInvoice(){
        SoftAssert soft = new SoftAssert();

        home.goToproductsPage();
        productPage.addToCart(fw.browser,productPage.addProduct1);
        productPage.viewCart();
        cartPage.checkout();
        cartPage.register_signin_BeforeCheckout();
        flow.fullSignUpFlow(userData);
        soft.assertTrue(home.isAccountCreatedSuccessfully());

        home.goToCart();
        flow.checkOutAndPaymentFlow(paymentData);
        soft.assertTrue(paymentPage.orderPlacedSuccessfully(),"invalid card data");

        paymentPage.downloadInvoice();
        paymentPage.continueAfterPayment();
        home.deleteAccount();
        soft.assertTrue(home.isAccountDeletedSuccessfully());

        soft.assertAll();
    }

    @Test

    public void  scrollUpUsingArrowButton(){
        home.scrollToBottom();
        Assert.assertTrue(home.isBottomTargetDisplayed());
        home.goUpByButton();
        Assert.assertTrue(home.isUpperTargetDisplayed());
    }

    @Test

    public void  scrollUpWithoutArrowButton(){
        home.scrollToBottom();
        Assert.assertTrue(home.isBottomTargetDisplayed());
        home.scrollToTop();
        Assert.assertTrue(home.isUpperTargetDisplayed());
    }


}
