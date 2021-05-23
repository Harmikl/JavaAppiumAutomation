package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {//

    protected AppiumDriver driver;
    public static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

@Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("udid", "1c88f784220d7ece");
        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
@Override
    protected void tearDown() throws Exception{
        driver.quit();
        super.tearDown();
    }
}
