import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
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

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }
    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCheckJavaWordInSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JAVA",
                "Cannot find 'Search…'",
                10
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                " Cannot find item containers '",
                5
        );
        List<WebElement> listOfElementsWithItemContainers =  driver.findElements(By.id("org.wikipedia:id/page_list_item_container")); //находим количество контейнеров

        MainPageObject.waitForElementPresent(
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
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);


    }
    @Test
    public void testAmountOfNotEmptySearch (){
        String search_line= "Linkin Park Diskography";
        String search_result_container ="//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        //  /* означает что мы спускаемся на один уровень ниже

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Search line'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_container),
                "Cannot find anything by request " + search_line ,
                15
        );
        int amount_of_search_result = MainPageObject.getAmountOfElement(
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

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Search line'",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching " + search_line,
                15
        );
        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text", // это атрибут из колонки Attribute в аппиуме
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
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
        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );

        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Search line'",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching " + search_line,
                15
        );

        driver.runAppInBackground(5); //отправляем аппку в бэк, через кол секунд в скобках аппка достанестя из бэка

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching " + search_line + "after background",
                15
        );
    }
        @Test
        public void testSaveTwoArticleToMyList(){
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia'",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find 'Search…'",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic searching by Java",
                    5
            );
//            MainPageObject.waitForElementPresent(
//                    By.id("org.wikipedia:id/view_page_title_text"),
//                    "Cannot find 'Java (programming language)'",
//                    10
//            );
            MainPageObject.waitTillElementBeClickable(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Element is not visible"
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot wait 'More options'",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),//класс в котором будем искать кнопку и в нем @content-desc
                    "Cannot find 'More options'",
                    10
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='Add to reading list']"),
                    //By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                    "Cannot find 'Add to reading list'",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    //By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                    "Cannot find 'Add to reading list'",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find 'Got it'",
                    5
            );
            MainPageObject.waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input field to set name of article",
                    5
            );
            String name_of_folder = "Learning programming";
            MainPageObject.waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Cannot find put 'Learning programming' in input field",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Cannot find 'OK' button ",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot tap 'X' icon",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia'",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Linkin Park Diskography",
                    "Cannot find 'Linkin Park Diskography'",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Linkin Park discography']"),
                    "Linkin Park discography",
                    15
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "cannot wait till more options is render 2 time",
                    10
            );
//            MainPageObject.waitTillElementBeClickable(
//                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
//                    "Element 'More option' is not visible"
//            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),//класс в котором будем искать кнопку и в нем @content-desc
                    "Cannot find 'More options'",
                    15
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='Add to reading list']"),
                    // By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                    "Cannot find 'Add to reading list' for second article",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                   // By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                    "Cannot find 'Add to reading list' for second article",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning programming']"),
                    "Cannot find " +name_of_folder,
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot tap 'X' icon",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find 'My lists' icon",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/item_title"),
                    "Cannot find 'Learning programming' in my lists",
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='"+ name_of_folder + "' ]"),
                    "Cannot find 'Learning programming' in my lists",
                    5
            );
            MainPageObject.swipeElementToLeft(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "cannot find Java (programming language) in My lists"
            );
            MainPageObject.waitForElementNotPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Still Java (programming language) in My lists",
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='Linkin Park discography']"),
                    "No Linkin Park discography in My lists",
                    5
            );
        }

        @Test
        public void testAssertTitleEx6(){
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia'",
                    5
            );
            String search_line="Java";
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_line,
                    "Cannot find 'Search…'",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic searching by "+search_line,
                    5
            );
            MainPageObject.assertElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text")
            );
        }

}
