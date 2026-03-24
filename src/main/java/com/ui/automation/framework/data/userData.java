package com.ui.automation.framework.data;

public class userData {
    public String Email;
    public String Password;
    public int dayOfBirth;
    public String monthOfBirth;
    public int yearOfBirth;
    public String signUpFirstName;
    public String signUpLastName;
    public String signUpCompany;
    public String signUpAddress1;
    public String signUpAddress2;
    public String signUpCountry;
    public String signUpState;
    public String signUpCity;
    public int signUpZipCode;
    public String signUpPhone;

    public userData() {
    }

    public void makeEmailChange() {
        if (this.Email != null) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            this.Email = this.Email.replace("@", timestamp + "@");
        }
    }
}