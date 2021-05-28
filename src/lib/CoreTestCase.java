package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.time.Duration;

import java.net.URL;

public class CoreTestCase extends TestCase {//
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;
    public static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

@Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        this.rotateScreenPortrait();
    }
@Override
    protected void tearDown() throws Exception{
        driver.quit();
        super.tearDown();
    }
    public void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    public void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds)); //отправляем аппку в бэк, через кол секунд в скобках аппка достанестя из бэка
    }
    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
            capabilities.setCapability("udid", "1c88f784220d7ece");
        }else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE My");
            capabilities.setCapability("platformVersion", "12.0");
            capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        }else {
            throw new Exception("Cannot get run platform from env variable. Platform value "+platform);
        }
        return capabilities;
    }
}
