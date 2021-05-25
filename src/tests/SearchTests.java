package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearchEx3() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found few results",
                amount_of_search_result > 0

        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "cxczxx";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testCheckJavaWordInSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "JAVA";
        SearchPageObject.typeSearchLine(search_line);
        String id_for_item_container = "org.wikipedia:id/page_list_item_container";
        SearchPageObject.waitForElementPresent(By.id(id_for_item_container), " Cannot find item containers", 5);
        List<WebElement> listOfElementsWithItemContainers = driver.findElements(By.id(id_for_item_container)); //находим количество контейнеров

        String xpath_for_java_contains = "//*[contains(@text,'Java')]";
        SearchPageObject.waitForElementPresent(By.xpath(xpath_for_java_contains), "Cannot find java in containers", 10);
        List<WebElement> listOfElementsWithJava = driver.findElements(By.xpath(xpath_for_java_contains)); //находим количество xpath с текстом 'Java'

        assertEquals(
                "List of containers is not equal to list of xpathes with 'Java' text",
                listOfElementsWithItemContainers.size(),
                listOfElementsWithJava.size()
        );
    }
        @Test
        public void testAssertTitleEx6 () {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
            MainPageObject MainPageObject = new MainPageObject(driver);
            MainPageObject.assertElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text")
            );
        }
        @Test
    public void testFindMoreThan3TitleAndDescription ()
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);
            SearchPageObject.initSearchInput();
            String search_line ="Java";
            SearchPageObject.typeSearchLine(search_line);
            int amount_of_titles_and_descriptions = SearchPageObject.getResultByTitleAndDescription();
            assertTrue(
                    "no one title and description by  request "+search_line,
                    amount_of_titles_and_descriptions>=3
            );
        }
    @Test
    public void testFindMTitleAndDescriptionByJavaRequest ()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java","Object-oriented programming language");
    }
    }

