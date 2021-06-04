package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject  extends MainPageObject{
     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT ,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION ,
            SEARCH_RESULT_BY_EXACT_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            TEST_ASSERT_TITLE_EX6;
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /*template methods*/
    private static String getResultSearchElement (String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultByExactTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_EXACT_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    public int  getResultByTitleAndDescription()
    {
        this.waitForElementPresent(SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION, "Cannot find title and description",10);
        return this.getAmountOfElement(SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION);
    }
    /*template methods*/
    public void initSearchInput (){ //при заруске находит поиск, тапает по нужному элементу, и проверяет что инпут есть
        this.waitForElementAndClick (SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"cannot find search cancel button", 5);
    }
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present", 5);
    }
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button",5);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line, "Cannot fine and type in to search input", 5);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"cannot find search result with substring " + substring);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementAndClick(search_result_xpath,"cannot find and click search result with substring " + substring, 10);
    }
    public void waitForElementByTitleAndDescription (String title, String description)
    {
        String title_of_article = getResultByExactTitleAndDescription(title,description);
        this.waitForElementPresent(title_of_article,"cannot find title and description"+title ,10);
    }
    public int getAmountOfFoundArticles ()
    {
        String search_line = "Linkin Park Diskography";


        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request " ,
                15
        );
        return this.getAmountOfElement(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element",15);

    }
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result");
    }
}
