package lib.UI.mobile_web;

import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
             MY_LIST_LINK ="css:a[data-event-name='watchlist']";
             OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }
    public MWNavigationUI(RemoteWebDriver driver)

    {
        super(driver);
    }
}
