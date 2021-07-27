package Tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    // 1 Тест проверки наличия определеного результата в выжаче поиска по ключевому слову Java
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    //Конец теста 1


    // 2 Тест проверки отмены поиска
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        //SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearcg();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    //Конец теста 2


    // 6 Тест поиска и проверки ненулевого результата через ассерт
    @Test
    public void testAmountOfNotEmptySearche()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }
    //Конец теста 6 и проверки ненулевого результата


    //7 Тест проверки пустого результата поиска
    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "lkjhgf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    //Конец теста 7 проверки пустого результата поиска
}
