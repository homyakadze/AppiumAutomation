package lib.UI.mobile_web;

import lib.UI.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(), '{TITLE}')]|/../../div[contains(@class, 'watched')]";
    }
    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
