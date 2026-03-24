package com.ui.automation.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class framework {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Actions> actionsThreadLocal = new ThreadLocal<>();

    private static final int DEFAULT_WAIT = 10;

    public WebDriver getBrowser() {
        return driverThreadLocal.get();
    }

    public Actions getActions() {
        return actionsThreadLocal.get();
    }

    // =========================
    // 1) Driver Setup & Init
    // =========================

    public WebDriver openBrowser(String browserName) {

        WebDriver createdDriver;

        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--remote-allow-origins=*");

            createdDriver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
            options.addPreference("dom.webnotifications.enabled", false);
            options.addPreference("geo.enabled", false);
            options.addPreference("permissions.default.desktop-notification", 2);

            createdDriver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        createdDriver.manage().window().maximize();
        createdDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        createdDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        driverThreadLocal.set(createdDriver);
        actionsThreadLocal.set(new Actions(createdDriver));

        return getBrowser();
    }

    // =========================
    // 2) Navigation
    // =========================

    public void navigate(String url) {
        getBrowser().get(url);
    }

    public void navigateBack() {
        getBrowser().navigate().back();
    }

    public void navigateForward() {
        getBrowser().navigate().forward();
    }

    public void refreshPage() {
        getBrowser().navigate().refresh();
    }

    public String getPageTitle() {
        return getBrowser().getTitle();
    }

    public String getCurrentURL() {
        return getBrowser().getCurrentUrl();
    }

    // =========================
    // 3) Waits
    // =========================

    public WebDriverWait getwait(int seconds) {
        return new WebDriverWait(getBrowser(), Duration.ofSeconds(seconds));
    }

    public void implicitWait(int seconds) {
        getBrowser().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void waitForUrlToContain(String fraction, int time) {
        getwait(time).until(ExpectedConditions.urlContains(fraction));
    }

    public void waitForUrlToChange(int seconds) {
        String currentUrl = getBrowser().getCurrentUrl();
        getwait(seconds).until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
    }

    public boolean waitForVisibility(By locator, int timeoutSeconds) {
        try {
            getwait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitForInvisibility(By locator, int timeoutSeconds) {
        getwait(timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void clickable(By locator, int timeoutSeconds) {
        getwait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
        Wait<WebDriver> fluentWait = new FluentWait<>(getBrowser())
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
            List<WebElement> elements = getBrowser().findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(By locator, int seconds) {
        WebElement field = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return field.getText().trim();
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
        ((JavascriptExecutor) getBrowser()).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                element
        );
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) getBrowser()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        ((JavascriptExecutor) getBrowser()).executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToElement(By locator, int seconds) {
        for (int i = 0; i < 2; i++) {
            try {
                WebElement element = getwait(seconds)
                        .until(ExpectedConditions.presenceOfElementLocated(locator));

                ((JavascriptExecutor) getBrowser()).executeScript(
                        "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                        element
                );

                getwait(seconds).until(ExpectedConditions.visibilityOf(element));
                return;

            } catch (StaleElementReferenceException e) {
                // retry
            }
        }

        Assert.fail("Failed to scroll to element: " + locator);
    }

    public void removeAds() {
        try {
            ((JavascriptExecutor) getBrowser()).executeScript(
                    "const selectors = [" +
                            "'ins.adsbygoogle'," +
                            "'iframe[id^=\"aswift\"]'," +
                            "'div[id^=\"aswift\"]'," +
                            "'#google_ads_rf'," +
                            "'.adsbygoogle'," +
                            "'iframe[src*=\"googleads\"]'," +
                            "'.vignette-fixed'," +
                            "'#ad_unit'," +
                            "'.ad-slot'," +
                            "'div[id^=\"google_ads_iframe\"]'," +
                            "'div[style*=\"position: fixed\"]'," +
                            "'div[class*=\"sticky\"]'," +
                            "'div[id*=\"sticky\"]'" +
                            "];" +
                            "selectors.forEach(selector => {" +
                            "document.querySelectorAll(selector).forEach(el => {" +
                            "el.style.visibility='hidden';" +
                            "el.style.display='none';" +
                            "el.style.pointerEvents='none';" +
                            "});" +
                            "});"
            );

            ((JavascriptExecutor) getBrowser()).executeScript(
                    "document.body.style.overflow='auto'; document.documentElement.style.overflow='auto';"
            );

            String currentUrl = getBrowser().getCurrentUrl();
            if (currentUrl.contains("#google_vignette")) {
                getBrowser().get(currentUrl.split("#")[0]);
            }
        } catch (Exception ignored) {
        }
    }

    // =========================
    // 6) Click Actions
    // =========================

    public void click(By locator, int seconds) {
        removeAds();
        scrollToCenter(locator, seconds);

        try {
            WebElement element = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            WebElement element = getwait(seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) getBrowser()).executeScript("arguments[0].click();", element);
        }
    }

    public void smartClick(By locator) {
        int count = getBrowser().findElements(locator).size();

        for (int i = 0; i < count; i++) {
            try {
                WebElement element = getBrowser().findElements(locator).get(i);

                if (element.isDisplayed() && element.isEnabled()) {
                    try {
                        element.click();
                        return;
                    } catch (Exception e) {
                        ((JavascriptExecutor) getBrowser()).executeScript("arguments[0].click();", element);
                        return;
                    }
                }
            } catch (StaleElementReferenceException e) {
                i--;
            } catch (Exception ignored) {
            }
        }

        Assert.fail("Could not click on element: " + locator);
    }

    public void rightClick(By locator, int seconds) {
        removeAds();
        scrollToCenter(locator, seconds);
        WebElement btn = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        getActions().contextClick(btn).perform();
    }

    public void clickUntilGone(By locator, int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            List<WebElement> elements = getBrowser().findElements(locator);

            if (elements.isEmpty()) {
                return;
            }

            try {
                if (!elements.get(0).isDisplayed()) {
                    return;
                }
            } catch (StaleElementReferenceException e) {
                return;
            }

            try {
                removeAds();
                scrollToCenter(locator, 5);
                WebElement element = getwait(5).until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                getwait(4).until(ExpectedConditions.invisibilityOfElementLocated(locator));
                return;
            } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException e) {
                try {
                    WebElement element = getBrowser().findElement(locator);
                    ((JavascriptExecutor) getBrowser()).executeScript("arguments[0].click();", element);
                    getwait(4).until(ExpectedConditions.invisibilityOfElementLocated(locator));
                    return;
                } catch (Exception ignored) {
                }
            }
        }

        Assert.fail("Element " + locator + " is still displayed after " + maxRetries + " attempts!");
    }

    // =========================
    // 7) Typing
    // =========================

    public void sendKeys(By locator, String text, int seconds) {
        removeAds();
        scrollToCenter(locator, seconds);

        WebElement field = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));

        try {
            field.click();
        } catch (Exception e) {
            ((JavascriptExecutor) getBrowser()).executeScript("arguments[0].click();", field);
        }

        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Keys.DELETE);
        field.sendKeys(text);
    }

    public void sendNumbers(By locator, int numbers, int seconds) {
        WebElement field = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        scrollToCenter(locator, seconds);
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Keys.DELETE);
        field.sendKeys(String.valueOf(numbers));
    }

    public void uploadFile(By locator, String fileName, int seconds) throws IOException {
        WebElement uploadElement = getwait(seconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        Path rawPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", fileName)
                .toAbsolutePath()
                .normalize();

        File file = rawPath.toFile();

        if (!file.exists() || !file.isFile()) {
            Assert.fail("File not found: " + rawPath);
        }

        String cleanPath = file.getCanonicalPath();

        uploadElement.sendKeys(cleanPath);
    }

    // =========================
    // 8) Dropdowns
    // =========================

    public void selectdropdownbyvisibletext(By locator, String visibleText, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(dropdown).selectByVisibleText(visibleText);
    }

    public void selectdropdownbyvalue(By locator, String value, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(dropdown).selectByValue(value);
    }

    public void selectdropdownbycontain(By locator, String text, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);

        for (WebElement option : select.getOptions()) {
            if (option.getText().contains(text)) {
                option.click();
                return;
            }
        }

        Assert.fail("No option contains text: " + text);
    }

    public void selectDropdownByIndex(By locator, int index, int seconds) {
        WebElement dropdown = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(dropdown).selectByIndex(index);
    }

    // =========================
    // 9) Mouse / Advanced Actions
    // =========================

    public void dragAndDrop(By sourceLocator, By targetLocator, int seconds) {
        WebElement first = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement second = getwait(seconds).until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        getActions().dragAndDrop(first, second).perform();
    }

    // =========================
    // 10) Checkbox & Radio
    // =========================

    public void checkCheckbox(By locator, int seconds) {
        WebElement checkbox = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void uncheckCheckbox(By locator, int seconds) {
        WebElement checkbox = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectRadioButton(By locator, int seconds) {
        WebElement radio = getwait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        if (!radio.isSelected()) {
            radio.click();
        }
    }

    // =========================
    // 11) Windows
    // =========================

    public void switchToWindowByTitle(String windowTitle) {
        for (String handle : getBrowser().getWindowHandles()) {
            getBrowser().switchTo().window(handle);
            if (getBrowser().getTitle().contains(windowTitle)) {
                return;
            }
        }

        Assert.fail("No window found with title containing: " + windowTitle);
    }

    public void switchToWindowByHandle(String windowHandle) {
        getBrowser().switchTo().window(windowHandle);
    }

    public void closeCurrentWindow() {
        getBrowser().close();
    }

    // =========================
    // 12) Alerts
    // =========================

    public void acceptAlert() {
        getwait(DEFAULT_WAIT).until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        getwait(DEFAULT_WAIT).until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public String getAlertText() {
        return getwait(DEFAULT_WAIT).until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void sendTextToAlert(String text) {
        Alert alert = getwait(DEFAULT_WAIT).until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
    }

    // =========================
    // 13) Close Driver
    // =========================

    public void closeBrowser() {
        WebDriver currentDriver = getBrowser();

        if (currentDriver != null) {
            try {
                currentDriver.quit();
            } finally {
                driverThreadLocal.remove();
                actionsThreadLocal.remove();
            }
        }
    }
}