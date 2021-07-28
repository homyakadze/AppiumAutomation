package lib.UI.mobile_web;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENET = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css::#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
