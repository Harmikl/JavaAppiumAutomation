package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE="id:org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "xpath://*[contains(@text,'View page in browser')]",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView",
    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    ARTICLE_OF_READING_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning programming']";
    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE,"cannot find article title on page", 15);
    }
    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "cannot find the end of article",
                20
        );
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
    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot tap 'X' icon",
                5
        );
    }
}
