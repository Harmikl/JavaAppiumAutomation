package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{
     protected static  String
     MY_LIST_LINK ,
     CLOSE_SYNC_YOUR_SAVED_ARTICLES;

    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }
    public void clickMyLists()
    {
        if (Platform.getInstance().isAndroid()){
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find 'My lists' icon",
                5);
        }else {
            this.waitForElementAndClick(
                    MY_LIST_LINK,
                    "Cannot find 'My lists' icon",
                    5);
            this.waitForElementAndClick(
                    CLOSE_SYNC_YOUR_SAVED_ARTICLES,
                    "Cannot find close icon on sync your saved articles",
                    5
            );
        }
    }
}
