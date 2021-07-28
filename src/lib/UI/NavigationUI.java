package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class NavigationUI extends MainPageObject
{
    protected static String
            MY_LIST_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if  (Platform.getInstance().isMW()){
        this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platforms " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyList()
    {
        if  (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Cannot find navigation button to My List",
                    5
            );
        }
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find navigation button to My List article",
                5
        );
    }
}
