package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject  extends MainPageObject{
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id ='org.wikipedia:id/page_list_item_title']/../*[@resource-id ='org.wikipedia:id/page_list_item_description']",
            SEARCH_RESULT_BY_EXACT_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{TITLE}')]/..//*[@text='{DESCRIPTION}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
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
