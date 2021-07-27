package Tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.MyListPageObject;
import lib.UI.NavigationUI;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    // 5 Тест сохранения статьи, открытия списка и удаления статьи из списка
    @Test
    public void testSaveFirstArticleToMyList() {
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

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    //Конец теста 5 сохранения статьи, открытия списка и удаления статьи из списка

}
