package com.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Basepage.BasePage;

/**
 * Registration typically asks for mobile number first, then sends an OTP.
 * We validate that the OTP screen/label is displayed after submitting a number.
 */
public class RegistrtionPage extends BasePage {

    public RegistrtionPage(long explicitWaitSec) {
        super(explicitWaitSec);
    }

    // Mobile number field on registration
    @FindBy(xpath = "//input[@type='text' and (@maxlength='10' or contains(@class,'_2IX_2-'))]")
    private WebElement mobileField;

    // Continue/Request OTP button
    @FindBy(xpath = "//button[contains(.,'CONTINUE') or contains(.,'Request OTP') or contains(.,'Continue')]")
    private WebElement continueBtn;

    // OTP label / heading that appears after submitting mobile
    @FindBy(xpath = "//*[contains(text(),'Enter OTP') or contains(.,'Verify with OTP') or contains(.,'OTP')]")
    private WebElement otpLabel;

    public void enterMobileAndContinue(String mobile) {
        wait.until(ExpectedConditions.visibilityOf(mobileField)).clear();
        mobileField.sendKeys(mobile);
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isOtpScreenDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(otpLabel));
            return otpLabel.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
