package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch (){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found few results",
                amount_of_search_result>0

        );
    }
    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "cxczxx";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
    @Test
    public void testCheckJavaWordInSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JAVA",
                "Cannot find 'Search…'",
                10
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                " Cannot find item containers '",
                5
        );
        List<WebElement> listOfElementsWithItemContainers =  driver.findElements(By.id("org.wikipedia:id/page_list_item_container")); //находим количество контейнеров

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Java')]"),
                "Cannot find 'Java '",
                5
        );
        List<WebElement> listOfElementsWithJava =  driver.findElements(By.xpath("//*[contains(@text,'Java')]")); //находим количество xpath с текстом 'Java'

        assertEquals(
                "List of containers is not equal to list of xpathes with 'Java' text",
                listOfElementsWithItemContainers.size(),
                listOfElementsWithJava.size()
        );

        @Test
        public void testAssertTitleEx6(){
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia'",
                    5
            );
            String search_line="Java";
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_line,
                    "Cannot find 'Search…'",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic searching by "+search_line,
                    5
            );
            MainPageObject.assertElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text")
            );
        }
}
