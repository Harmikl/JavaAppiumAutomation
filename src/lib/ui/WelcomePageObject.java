package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{
    public static final String
    STEP_MORE_LEARN_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_LINK = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_LINK = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started",
    SKIP = "id:Skip";
    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void waitForLearnMoreLink ()
    {
        this.waitForElementPresent(STEP_MORE_LEARN_LINK, "Cannot find learn more about",10);
    }
    public void waitNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_LINK,"Cannot find 'New ways to explore' text", 10);
    }
    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_LINK,"Cannot find 'Add or edit preferred languages' text", 10);
    }
    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Cannot find 'Learn more about data collected' text", 10);
    }
    public void clickNextButton ()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' button",10);
    }
    public void clickGetStartedButton ()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' button",10);
    }
    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannot find and click Skip ", 5);
    }
}

