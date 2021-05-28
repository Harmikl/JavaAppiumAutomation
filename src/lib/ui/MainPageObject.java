package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver; //инициализируем драйвер
    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds ) ;
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath or id и затем происходит клик
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath и затем вводит значение
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {//метод проверяет что элемента нету
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x  = size.width/2;
        int start_y = (int) (size.height*0.8); //приводим к int потому что хз будет double или int
        int end_y = (int) (size.height*0.2);
        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();// тут мы написали что нужно нажать, сместить, потом опять нажать и все это запустить

    }
    public void swipeUpQuick(){
        swipeUp(200);
    }
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by = this.getLocatorByString(locator);
        int already_swiped=0;
        while (driver.findElements(by).size()==0){//эта функция находит все элементы

            if(already_swiped > max_swipes){
                waitForElementPresent(locator,"Cann not find element by swiping up\n"+error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }
    public void swipeElementToLeft(String locator, String error_message){
        WebElement element = waitForElementPresent(locator,error_message,10);
        int left_x = element.getLocation().getX(); //это запишет самую левую координату п оси x
        int right_x = left_x + element.getSize().getWidth();//это запишет самую правую координату п оси x
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
                action.press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x,middle_y))
                .release()
                .perform();
    }
    public  int getAmountOfElement(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        // List - фуекция которая создает некий список, element -  название переменной,
        // то есть мы возвращаем количество элементов, которые нашли при помощи  driver.findElements(by)
        return elements.size();
    }
    public String waitForElementAndGetAttribute(String locator,String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent( locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    public void assertElementPresent(String locator ){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        int count_of_titles= elements.size();
        int count_of_titles_expected=1;
        Assert.assertEquals(
                "There are no title found",
                count_of_titles_expected,
                count_of_titles
        );
    }
    public void assertElementNotPresent(String locator,String error_message ){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        int count_of_titles= elements.size();
        int count_of_titles_expected=0;
        Assert.assertEquals(
                "There are no title found",
                count_of_titles_expected,
                count_of_titles
        );
    }
    public void waitTillElementBeClickable(String locator, String error_message){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    private By getLocatorByString (String locator_with_type)
    {
        String [] exploded_locator = locator_with_type.split(Pattern.quote(":"),2); // делим строку на 2 части по символу :
        String by_type = exploded_locator[0]; // сюда передается первая часть
        String locator = exploded_locator[1]; // сюда вторая

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        }else if (by_type.equals("id")){
            return By.id(locator);
        }else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator "+locator_with_type);
        }
    }
}


