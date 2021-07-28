package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;


public class MyListPageObject extends MainPageObject
{
    private static final String
            FOLDER_MY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']",
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_MY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find article in My List",
                5
        );
    }

    public void waitForArticleTitleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath),"Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleTitleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath),"Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleTitleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        if  (Platform.getInstance().isIOS()) || Platform.getInstance().isAndroid()){
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
    } else {
        String remove_locator = getRemoveButtonByTitle(article_title);
        this.waitForElementAndClick(
                remove_locator,
                "Cannot click button to remove article from saved",
                10
        );
    }

    if (Platform.getInstance().isMW(){
        driver.navigate().refresh();
    }
        this.waitForArticleTitleToDisappearByTitle(article_title);
    }

}
