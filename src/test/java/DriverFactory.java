

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser, long implicitWaitSec) {
        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            WebDriver driver = WebDriverManager.chromedriver().capabilities(options).create();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitSec));
            tlDriver.set(driver);
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
