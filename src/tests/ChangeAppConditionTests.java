package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangesScreenOrientationOnSearchResults(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Title of artical is changed after rotation",
                title_after_rotation,
                title_before_rotation

        );
        this.rotateScreenPortrait();
        String title_after_second_rotation= ArticlePageObject.getArticleTitle();
        assertEquals(
                "Title of artical is changed after rotation",
                title_after_second_rotation,
                title_before_rotation

        );

    }
    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(5);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
