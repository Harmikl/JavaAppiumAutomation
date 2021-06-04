package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static
    {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        ARTICLE_IS_NOT_DELETED = "id:Linkin Park discography Band discography"; //xpath://XCUIElementTypeLink[@name='Linkin Park discography Band discography']

    }
    public IOSMyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
