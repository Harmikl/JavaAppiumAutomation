package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE="org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[contains(@text,'View page in browser')]",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE),"cannot find article title on page", 15);
    }
    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "cannot find the end of article",
                20
        );
    }
    public void addArticleToMyList (String name_of_folder)
    {
        this.waitTillElementBeClickable(
                By.xpath(OPTIONS_BUTTON),
                "Element more options is not visible"
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),//класс в котором будем искать кнопку и в нем @content-desc
                "Cannot find 'More options'",
                10
        );
        this.waitForElementAndClick(
                //By.xpath("//*[@text='Add to reading list']"),
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to reading list'",
                10
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it'",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input field to set name of article",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find put 'Learning programming' in input field",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find 'OK' button ",
                5
        );
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot tap 'X' icon",
                5
        );
    }
}
