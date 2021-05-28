package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class IOSTestCase extends TestCase {//

    protected AppiumDriver driver;
    public static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

@Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE My");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        driver = new IOSDriver(new URL(AppiumUrl), capabilities);
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
}
