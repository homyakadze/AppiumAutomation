package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ArticlePageObject extends MainPageObject
{
    protected static String
    FOOTER_ELEMENET = "xpath://*[@text='View page in browser']",
    TITLE = "id:org.wikipedia:id/view_page_title_text",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;



    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE),"Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }

    }

    //Метод assertElementPresent для Ex6 задания рефакторинга (Ex8)
    public void assertElementPresent()
    {
        List elements = driver.findElements(TITLE);
        Assert.assertTrue(
                "The article has no title",
                elements.size() == 1);
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENET,
                "CAnnot find the end of erticle",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
       this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article option",
                10
        );

       this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find options to add article to reading list",
                10
        );

       this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'GOT IT' tip overlay",
                5
        );

       this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5
        );

       this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

       this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isAndroid() || Platform.getInstance.isIOS()){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        }
            ArticlePageObject.addArticlesToMySaved(name_of_folder);
        } else {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()){
            this.removeArticlesToMySavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list");
    }

    public void removeArticlesToMySavedIfItAdded()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove an article from saver", 5);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved list after removing it from this list before", 5);

        }
