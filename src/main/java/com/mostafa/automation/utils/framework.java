package com.mostafa.automation.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.util.List;
import java.time.Duration;

public class framework {

    public WebDriver browser;
    Actions a1 ;

    // =========================
    // 1) Driver Setup & Init
    // =========================

    public WebDriver openBrowser() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

         browser = new ChromeDriver(options);

    //    browser = new ChromeDriver();
        browser.manage().window().maximize();
        a1 = new Actions(browser);
        return browser;
    }

    // =========================
    // 2) Navigation
    // =========================

    public void navigate(String url) {
        browser.get(url);
    }

    public void navigateBack(){
        browser.navigate().back();
    }

    public void navigateForward(){
        browser.navigate().forward();
    }

    public void refreshPage(){
        browser.navigate().refresh();
    }

    public String getPageTitle() {
        return browser.getTitle();
    }

    public String getCurrentURL() {
        return browser.getCurrentUrl();
    }

    // =========================
    // 3) Waits
    // =========================

    public  WebDriverWait getwait(int seconds){
        return new WebDriverWait(browser,Duration.ofSeconds(seconds));
    }

    public void implicitWait(int seconds) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void waitForUrlToContain(String fraction, int time) {
        WebDriverWait wait = new WebDriverWait(browser , Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    public void waitForUrlToChange(int seconds) {

        String currentUrl = browser.getCurrentUrl();
        getwait(seconds).until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
    }

    public boolean waitForVisibility(By locator, int timeoutSeconds) {
        getwait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return true;
    }

    public void waitForInvisibility(By locator, int timeoutSeconds) {
        getwait(timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void clickable(By locator, int timeoutSeconds) {
        getwait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String
            timeoutMessage) {
        Wait<WebDriver> fluentWait = new FluentWait<>(browser)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage(timeoutMessage);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // =========================
    // 4) Element Checks & Getters
    // =========================

    public boolean isElementDisplayed(By locator) {
        try {
            return browser.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(By locator , int seconds) {
        WebElement field = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return field.getText();
    }

    public int getInt(By locator, int time) {
        String text = getText(locator, time).trim();
        return Integer.parseInt(text);
    }

    public String getValue(By locator, int seconds) {
        WebElement field = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return field.getAttribute("value");
    }

    // =========================
    // 5) Scroll / Page Utilities
    // =========================

    public void scrollToCenter(By locator, int seconds) {

        WebElement element = getwait(seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void scrollToElement(By locator,int seconds){

        WebElement element = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        a1.scrollToElement(element).perform();
    }

    public void removeAds() {
        try {
            ((JavascriptExecutor) browser).executeScript(
                    "const selectors = [" +
                            "    'ins.adsbygoogle', 'iframe[id^=\"aswift\"]', 'div[id^=\"aswift\"]', " +
                            "    '#google_ads_rf', '.adsbygoogle', 'iframe[src*=\"googleads\"]', " +
                            "    '.vignette-fixed', '#ad_unit', '.ad-slot', 'div[id^=\"google_ads_iframe\"]', " +
                            "    'div[style*=\"position: fixed\"]', 'div[class*=\"sticky\"]', 'div[id*=\"sticky\"]' " + // زيادة هنا
                            "];" +
                            "selectors.forEach(selector => {" +
                            "    document.querySelectorAll(selector).forEach(el => {" +
                            "        el.style.visibility = 'hidden';" + // بيخليها شفافة تماماً
                            "        el.style.display = 'none';" +    // بيشيل مساحتها من الصفحة
                            "        el.style.pointerEvents = 'none';" + // بيخلي الضغطات تعدي من فوقيها
                            "    });" +
                            "});"
            );

            ((JavascriptExecutor) browser).executeScript(
                    "document.body.style.overflow = 'auto'; document.documentElement.style.overflow = 'auto';"
            );

            String currentUrl = browser.getCurrentUrl();
            if (currentUrl.contains("#google_vignette")) {
                browser.get(currentUrl.split("#")[0]);
            }
        } catch (Exception e) {

        }

    }

    // =========================
    // 6) Click Actions
    // =========================

    public void click(By locator, int seconds) {
        removeAds();
        scrollToCenter(locator, seconds);
        getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void smartClick(WebDriver driver, By locator) {
        int count = driver.findElements(locator).size();

        for (int i = 0; i < count; i++) {
            try {
                WebElement element = driver.findElements(locator).get(i);

                if (element.isDisplayed() && element.isEnabled()) {
                    try {
                        element.click();
                        return;
                    } catch (Exception e) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                        return;
                    }
                }
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                i--;
            } catch (Exception e) {
                continue;
            }
        }
    }

    public void rightClick(By locator, int seconds) {
        removeAds();
        scrollToCenter(locator,seconds);
        WebElement btn = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        a1.contextClick(btn).perform();
    }

    public void clickUntilGone(By locator, int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            List<WebElement> elements = browser.findElements(locator);
            if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                System.out.println("Success: Element is gone.");
                return;
            }
            try {
                scrollToCenter(locator ,5);
                removeAds();
                elements.get(0).click();
                System.out.println("Attempt " + (i + 1) + ": Clicked, waiting to see if it disappears...");
            } catch (Exception e) {

            }
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
        Assert.fail("Element " + locator + " is still displayed after " + maxRetries + " attempts!");
    }


    // =========================
    // 7) Typing
    // =========================

//    public void sendKeys(By locator, String text , int seconds) {
//        WebElement field = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
//        scrollToCenter(locator, seconds);
//        field.click();
//        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        field.sendKeys(text);
//    }
public void sendKeys(By locator, String text, int seconds) {

    getwait(seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    scrollToCenter(locator, seconds);

    WebElement field = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));

    a1.moveToElement(field).click().perform();
    field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    field.sendKeys(text);
}

    public void sendNumbers(By locator, int numbers, int seconds) {
        WebElement field = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(String.valueOf(numbers));
    }

    // =========================
    // 8) Dropdowns
    // =========================

    public void selectdropdownbyvisibletext(By locator, String visibleText, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public void selectdropdownbyvalue(By locator, String value, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public void selectdropdownbycontain(By locator, String text, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);

        for (WebElement option : select.getOptions()) {
            if (option.getText().contains(text)) {
                option.click();
                break;
            }
        }
    }

    public void selectDropdownByIndex(By locator, int index , int seconds){
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // =========================
    // 9) Mouse / Advanced Actions
    // =========================

    public void dragAndDrop(By sourceLocator, By targetLocator , int seconds){
        WebElement first = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement second = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        a1.dragAndDrop(first,second).perform();
    }

    // =========================
    // 10) Checkbox & Radio
    // =========================

    public void checkCheckbox(By locator , int seconds){
        WebElement checkbox = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void uncheckCheckbox(By locator , int seconds){
        WebElement checkbox = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectRadioButton(By locator , int seconds){
        WebElement radio =
                getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));

        if (!radio.isSelected()) {
            radio.click();
        }
    }

    // =========================
    // 11) Windows
    // =========================

    public void switchToWindowByTitle(String windowTitle){
        for (String handle : browser.getWindowHandles()) {
            browser.switchTo().window(handle);

            if (browser.getTitle().contains(windowTitle)) {
                break;
            }
        }
    }

    public void switchToWindowByHandle(String windowHandle){
        browser.switchTo().window(windowHandle);
    }

    public void closeCurrentWindow(){
        browser.close();
    }

    // =========================
    // 12) Alerts
    // =========================

    public void acceptAlert(){
        browser.switchTo().alert().accept();
    }

    public void dismissAlert(){
        browser.switchTo().alert().dismiss();
    }

    public String getAlertText(){
        String text = browser.switchTo().alert().getText();
        return text;
    }

    public void sendTextToAlert(String text){
        browser.switchTo().alert().sendKeys(text);
    }

    // =========================
    // 13) Close Driver
    // =========================

    public void closeBrowser(){
        browser.quit();
    }

}
