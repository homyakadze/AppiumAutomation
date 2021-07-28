package lib.UI.mobile_web;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

        static {

            SEARCH_INIT_ELEMENT = "css:button#searchIcon",
            SEARCH_INPUT = "css:form>input[type='search']",
            SEATCH_CANCEL_BUTTON = "css:button.cancel",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikipedia-description')][contains(text(),'{SUBSTRING}')]",
            SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
            SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        }

        public MWSearchPageObject(RemoteWebDriver driver)
        {
            super(driver);
        }
}
