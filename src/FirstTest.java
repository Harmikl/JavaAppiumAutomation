import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("udid", "1c88f784220d7ece");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementByXpathAndClick( // два слеша означают любую вложенность элементов, * то есть любой элемент, [contains] выражение позволяющее искать по частичному совпадению
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementByXpathAndSendKeys(
                "//*[contains(@text,'Search…')]",
                "Java",
                "Cannot find 'Search…'",
                5
        );
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementByIdhAndClick(
                "org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementByIdhAndClick(
                "org.wikipedia:id/search_close_btn",
                "Cannot find close icon",
                5
        );
        waitForElementNotPresent(
                "org.wikipedia:id/search_close_btn",
                "X is still on the page",
                5
        );
    }


    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message) {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath и затем происходит клик
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath и затем вводит значение
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementByIdhAndClick(String id, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в id и затем происходит клик
        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private boolean waitForElementNotPresent(String id, String error_message, long timeoutInSeconds){//метод проверяет что элемента нету
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}