package com.mostafa.automation.pages;

import org.openqa.selenium.By;
import com.mostafa.automation.data.contactUsData;
import com.mostafa.automation.utils.framework;

public class contactUsPage {
    framework fw;
    private final  By contactUsButton = By.xpath("//a[@href='/contact_us']");
    private final By contactUs_nameField = By.xpath("//input[@name='name']");
    private final By contactUs_emailField = By.xpath("//input[@name='email']");
    private final By contactUs_subjectField = By.xpath("//input[@name='subject']");
    private final By contactUs_msgField= By.xpath("//textarea[@name='message']");
    private final By chooseFileButton = By.xpath("//input[@name='upload_file']");
    private final By submitButton = By.xpath("//input[@type='submit']");
    private final By successMsg = By.xpath("//div[@class='status alert alert-success']");
    private final By goHomeButton = By.xpath("//a[@class='btn btn-success']");

    public  contactUsPage (framework fw){
        this.fw=fw;
    }

    public void fillContactUsForm(contactUsData data) {

        String fullPath = System.getProperty("user.dir") + "/src/test/resources/" + data.contactUsFilePath;
        fw.click(contactUsButton,5);
        fw.sendKeys(contactUs_nameField,data.contactUsName,5);
        fw.sendKeys(contactUs_emailField,data.contactUsEmail,5);
        fw.sendKeys(contactUs_subjectField,data.contactUsSubject,5);
        fw.sendKeys(contactUs_msgField,data.contactUsMsg,5);
        fw.browser.findElement(chooseFileButton).sendKeys(fullPath);
        fw.click(submitButton,5);
        fw.browser.switchTo().alert().accept();

    }

    public boolean isContactingDoneSuccessfully() {
        return fw.isElementDisplayed(successMsg);
    }
}

