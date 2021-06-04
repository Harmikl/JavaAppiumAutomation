package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        CLOSE_SYNC_YOUR_SAVED_ARTICLES = "id:Close";
    }
    public IOSNavigationUI (AppiumDriver driver)
    {
        super(driver);
    }
}
