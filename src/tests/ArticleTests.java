package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
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
}
