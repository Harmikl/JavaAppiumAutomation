package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
            TITLE,
            TITLE_1,
    FOOTER_ELEMENT ,
    OPTIONS_BUTTON ,
    OPTIONS_ADD_TO_MY_LIST_BUTTON ,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT ,
    MY_LIST_OK_BUTTON ,
    CLOSE_ARTICLE_BUTTON ,
    ARTICLE_OF_READING_LIST ,
    CANCEL_BUTTON_ON_SEARCH_PAGE;
    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement() {
            return this.waitForElementPresent(TITLE, "cannot find article title on page", 15);
    }
    public WebElement waitForTitleElement1()
    {
        return this.waitForElementPresent(TITLE_1,"cannot find article 1 title on page",15);
    }
    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else {
            return title_element.getAttribute("name");
        }
    }
    public String getArticleTitle1 ()
    {
        WebElement title_element = waitForTitleElement1();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else {
            return title_element.getAttribute("name");
        }
    }
    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "cannot find the end of article",
                    40);
        }else this.swipeUpTillElementAppear(
                FOOTER_ELEMENT,
                "Cannot find the end of article in ios",
                40);
    }
    public void addArticleToMyList (String name_of_folder)
    {
        this.waitTillElementBeClickable(
                OPTIONS_BUTTON,
                "Element more options is not visible"
        );
        this.waitForElementAndClick(
                OPTIONS_BUTTON,//класс в котором будем искать кнопку и в нем @content-desc
                "Cannot find 'More options'",
                10
        );
        this.waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']"),
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list'",
                10
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it'",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input field to set name of article",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find put 'Learning programming' in input field",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button ",
                5
        );
    }
    public void addArticleToMyListSecondTime (String name_of_folder)
    {
        this.waitTillElementBeClickable(
                OPTIONS_BUTTON,
                "Element more options is not visible"
        );
        this.waitForElementAndClick(
                OPTIONS_BUTTON,//класс в котором будем искать кнопку и в нем @content-desc
                "Cannot find 'More options'",
                10
        );
        this.waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']"),
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list'",
                10
        );
        this.waitForElementAndClick(
                ARTICLE_OF_READING_LIST,
                "Cannot find " +name_of_folder,
                5
        );

    }
    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot tap 'X' icon",
                5
        );
    }
    public void cancelOnSearchField()
    {
        this.waitForElementAndClick(
                CANCEL_BUTTON_ON_SEARCH_PAGE,
                "Cannot find cancel button on serch page",
                10);
    }
}
