import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        //capabilities.setCapability("udid", "1c88f784220d7ece");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick( // два слеша означают любую вложенность элементов, * то есть любой элемент, [contains] выражение позволяющее искать по частичному совпадению
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find 'Search…'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick( // два слеша означают любую вложенность элементов, * то есть любой элемент, [contains] выражение позволяющее искать по частичному совпадению
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find 'Search…'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );
        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Java (programming language)'",
                10
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick( // два слеша означают любую вложенность элементов, * то есть любой элемент, [contains] выражение позволяющее искать по частичному совпадению
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find 'Search…'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'Appium')]"),
                "Cannot find 'Appium'",
                5
        );
        swipeUpToFindElement(
                By.xpath("//*[contains(@text,'View page in browser')]"),
                "Cannot find the end of the article",
                5
        );


    }
    @Test
    public void cancelSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Weather",
                "Cannot find 'Search…'",
                10
        );
        waitForElementPresent(//1st search result
                By.xpath("//*[contains(@text,'State of the atmosphere')]"),
                "Cannot find 'State of the atmosphere'",
                5
        );
        waitForElementPresent(//2nd search result
                By.xpath("//*[contains(@text,'American radical organization')]"),
                "Cannot find 'American radical organization'",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear 'Weather'",
                5
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'State of the atmosphere')]"),
                "'State of the atmosphere' is still here",
                5
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'American radical organization')]"),
                "'American radical organization' is still here",
                5
        );
    }

    @Test
    public void checkJavaWordInSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JAVA",
                "Cannot find 'Search…'",
                10
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                " Cannot find item containers '",
                5
        );
        List<WebElement> listOfElementsWithItemContainers =  driver.findElements(By.id("org.wikipedia:id/page_list_item_container")); //находим количество контейнеров

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java')]"),
                "Cannot find 'Java '",
                5
        );
        List<WebElement> listOfElementsWithJava =  driver.findElements(By.xpath("//*[contains(@text,'Java')]")); //находим количество xpath с текстом 'Java'

        Assert.assertEquals(
                "List of containers is not equal to list of xpathes with 'Java' text",
                listOfElementsWithItemContainers.size(),
                listOfElementsWithJava.size()
        );
    }
    @Test
    public void saveFirstArticleToMyList(){
        waitForElementAndClick( // два слеша означают любую вложенность элементов, * то есть любой элемент, [contains] выражение позволяющее искать по частичному совпадению
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find 'Search…'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Java (programming language)'",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),//класс в котором будем искать кнопку и в нем @content-desc
                "Cannot find 'More options'",
                10
        );
        waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find 'Add to reading list'",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it'",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field to set name of article",
                5
        );
        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find put 'Learning programming' in input field",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find 'OK' button ",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot tap 'X' icon",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' icon",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find 'Learning programming' in my lists",
                5
        );
        waitForElementPresent(
             By.xpath("//*[@text='"+ name_of_folder + "' ]"),
                "Cannot find 'Learning programming' in my lists",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "cannot find Java (programming language) in My lists"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Still Java (programming language) in My lists",
                5
        );

    }
    @Test
    public void testAmountOfNotEmptySearch (){
        String search_line= "Linkin Park Diskography";
        String search_result_container ="//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        //  /* означает что мы спускаемся на один уровень ниже

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Search line'",
                5
        );

        waitForElementPresent(
                By.xpath(search_result_container),
                "Cannot find anything by request " + search_line ,
                15
        );
        int amount_of_search_result = getAmountOfElement(
                By.xpath(search_result_container)
        );
        Assert.assertTrue(
                "We found few results",
                amount_of_search_result>0

        );
    }

    @Test
    public void testChangesScreenOrientationOnSearchResults(){
        String search_line= "Java";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Search line'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching " + search_line,
                15
        );
        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text", // это атрибут из колонки Attribute в аппиуме
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text", // это атрибут из колонки Attribute в аппиуме
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Title of artical is changed after rotation",
                title_after_rotation,
                title_before_rotation

        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Title of artical is changed after rotation",
                title_after_second_rotation,
                title_before_rotation

        );

    }
    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Search line'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching " + search_line,
                15
        );

        driver.runAppInBackground(5); //отправляем аппку в бэк, через кол секунд в скобках аппка достанестя из бэка

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching " + search_line + "after background",
                15
        );
    }
        @Test
        public void saveTwoArticleToMyList(){
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia'",
                    5
            );
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find 'Search…'",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic searching by Java",
                    5
            );
            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find 'Java (programming language)'",
                    10
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),//класс в котором будем искать кнопку и в нем @content-desc
                    "Cannot find 'More options'",
                    10
            );
            waitForElementAndClick(
                    //By.xpath("//*[@text='Add to reading list']"),
                    By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                    "Cannot find 'Add to reading list'",
                    10
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find 'Got it'",
                    5
            );
            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input field to set name of article",
                    5
            );
            String name_of_folder = "Learning programming";
            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Cannot find put 'Learning programming' in input field",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Cannot find 'OK' button ",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot tap 'X' icon",
                    5
            ); waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia'",
                    5
            );
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Linkin Park Diskography",
                    "Cannot find 'Linkin Park Diskography'",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Linkin Park discography']"),
                    "Linkin Park discography",
                    15
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),//класс в котором будем искать кнопку и в нем @content-desc
                    "Cannot find 'More options'",
                    15
            );
            waitForElementAndClick(
                    //By.xpath("//*[@text='Add to reading list']"),
                    By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                    "Cannot find 'Add to reading list' for second article",
                    15
            );


            waitForElementAndClick(
                            By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning programming']"),
                            "Cannot find " +name_of_folder,
                            5
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot tap 'X' icon",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find 'My lists' icon",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/item_title"),
                    "Cannot find 'Learning programming' in my lists",
                    5
            );
            waitForElementPresent(
                    By.xpath("//*[@text='"+ name_of_folder + "' ]"),
                    "Cannot find 'Learning programming' in my lists",
                    5
            );
            swipeElementToLeft(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "cannot find Java (programming language) in My lists"
            );
            waitForElementNotPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Still Java (programming language) in My lists",
                    5
            );
            waitForElementPresent(
                    By.xpath("//*[@text='Linkin Park discography']"),
                    "No Linkin Park discography in My lists",
                    5
            );
        }





    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath or id и затем происходит клик
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {//написали метод который сначала дожидается какого-то
        //элемента которыый передается в xpath и затем вводит значение
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {//метод проверяет что элемента нету
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x  = size.width/2;
        int start_y = (int) (size.height*0.8); //приводим к int потому что хз будет double или int
        int end_y = (int) (size.height*0.2);
        action
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();// тут мы написали что нужно нажать, сместить, потом опять нажать и все это запустить

    }
    protected void swipeUpQuick(){
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped=0;
        while (driver.findElements(by).size()==0){//эта функция находит все элементы

            if(already_swiped > max_swipes){
                waitForElementPresent(by,"Cann not find element by swiping up\n"+error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }
    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by,error_message,10);
        int left_x = element.getLocation().getX(); //это запишет самую левую координату п оси x
        int right_x = left_x + element.getSize().getWidth();//это запишет самую правую координату п оси x
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
    private int getAmountOfElement(By by){
        List elements = driver.findElements(by);
        // List - фуекция которая создает некий список, element -  название переменной,
        // то есть мы возвращаем количество элементов, которые нашли при помощи  driver.findElements(by)
        return elements.size();
    }
    private String waitForElementAndGetAttribute(By by,String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent( by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
