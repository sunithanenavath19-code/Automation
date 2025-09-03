package Homepage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Basepage.BasePage;

/**
 * Flipkart often launches a login/register modal. 
 * We target the "New to Flipkart? Create an account" link on the modal.
 * Locators may change; these are resilient CSS/XPath examples.
 */
public class HomePage extends BasePage {

    public HomePage(long explicitWaitSec) {
        super(explicitWaitSec);
    }

    // If a close button appears on initial login popup (sometimes '✕')
    @FindBy(css = "button._2KpZ6l._2doB4z")
    private WebElement closeLoginPopupBtn; // if present

    // "New to Flipkart? Create an account" link (inside login/register modal)
    @FindBy(xpath = "//*[contains(text(),'New to Flipkart?')]/following-sibling::a[contains(.,'Create an account')]")
    private WebElement createAccountLink;

    public void open(String url) {
        driver.get(url);
    }

    public void closeLoginPopupIfVisible() {
        try {
            if (closeLoginPopupBtn.isDisplayed()) {
                closeLoginPopupBtn.click();
            }
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {}
    }

    public void clickCreateAccount() {
        // If popup is closed automatically, registration link may be elsewhere.
        // Try the modal first; if not found, open login flow from header.
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createAccountLink)).click();
        } catch (TimeoutException e) {
            // fallback: open login menu and then find a register link
            WebElement loginHeader = driver.findElement(By.xpath("//a[contains(@href,'account/login')] | //span[text()='Login']"));
            loginHeader.click();
            wait.until(ExpectedConditions.elementToBeClickable(createAccountLink)).click();
        }
    }
}
