

import io.cucumber.java.en.*;
import org.testng.Assert;

import Homepage.HomePage;

public class RegisterSteps {

    private HomePage home;
    private RegisterSteps register;

    public RegisterSteps() {
        long explicitWait = Long.parseLong(Hooks.get("explicitWait"));
        home = new HomePage(explicitWait);
        register = new RegisterSteps();
    }

    @Given("I open the Flipkart app in a browser")
    public void i_open_flipkart() {
        home.open(Hooks.get("baseUrl"));
        home.closeLoginPopupIfVisible(); // optional safety
    }

    @When("I click the {string} link")
    public void i_click_create_account_link(String _ignored) {
        home.clickCreateAccount();
    }

    @When("I enter mobile number {string} and proceed")
    public void i_enter_mobile_and_proceed(String mobile) {
        register.enterMobileAndContinue(mobile);
    }

    @Then("I should see the OTP verification screen")
    public void i_should_see_otp_screen() {
        Assert.assertTrue(register.isOtpScreenDisplayed(),
                "Expected OTP screen to be visible after submitting mobile number.");
    }
}
