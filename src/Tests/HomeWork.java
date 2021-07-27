package Tests;

import lib.CoreTestCase;
import lib.UI.*;
import org.junit.Test;

public class HomeWork extends CoreTestCase {

    //Home work Refactoring Ex6
    @Test
    public void testOpenArticleAndAssertTitlePresent() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertElementPresent();

    }
    //Finish home work refactoring Ex6


    //Home work refactoring Ex5
    @Test
    public void testSearchTwoArticles() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Wikimedia list article");

        ArticlePageObject.waitForTitleElement();
        article_title = ArticlePageObject.getArticleTitle();
        name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        MyListPageObject  MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete("Java (programming language)");

        SearchPageObject.waitForSearchResult("Wikimedia list article");
        SearchPageObject.clickByArticleWithSubstring("Java version history");

        article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java version history",
                article_title
        );
    }
    //Finish home work refactoring Ex5


    //Home work refactoring EX3
    @Test
    public void testCancelSearchFewResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Ivideon";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 1
        );

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForNoResultsLabel();
    }
    //Finish home work refactoring Ex3
}
