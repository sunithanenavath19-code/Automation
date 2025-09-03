

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.FileInputStream;
import java.util.Properties;

public class Hooks {
    private static Properties props;

    @Before
    public void setUp() throws Exception {
        props = new Properties();
        props.load(new FileInputStream("src/test/resources/config/config.properties"));

        String browser = props.getProperty("browser", "chrome");
        long implicitW = Long.parseLong(props.getProperty("implicitWait", "5"));
        DriverFactory.initDriver(browser, implicitW);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
