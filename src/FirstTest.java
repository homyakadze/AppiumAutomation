import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest
{

    private AppiumDriver driver;

    //Первый тест, запуск на реальном Nexus 5
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/achumak/Desktop/AppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }



    @After
    public void tearDown()
    {
        driver.quit();
    }



    //Тест проверки наличия определеного результата в выжаче поиска по ключевому слову Java

    @Test
    public void testForSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                10
        );

    }
    //Конец теста


    //Тест проверки отмены поиска

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }
    //Конец теста



    //Тест сравнения ожидаемой статьи

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot fint article title",
                15
        );

        String article_title = title_element.getAttribute(("text"));

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }
    //Конец теста


    //Тест свайпа статьи

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot fint article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the artile",
                20
        );

    }
    //Конец теста свайпа статьи


    //Тест сохранения статьи, открытия списка и удаления статьи из списка

    @Test
    public void saveFirstArticleToMyList()
    {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia input",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text, 'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find Search Wikipedia input",
                    5
            );

            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot fint article title",
                    15
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find button to open article option",
                    10
            );


            waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    "Cannot find options to add article to reading list",
                    10
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find 'GOT IT' tip overlay",
                    5
            );

            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input to set name of article folder",
                    5
            );

            String name_of_folder = "Learning programming";

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Cannot put text into articles folder input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Cannot press OK button",
                    5
            );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find article in My List",
                5
        );


        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );

    }

    //Конец теста сохранения статьи, открытия списка и удаления статьи из списка


    //Тест поиска и проверки ненулевого результата через ассерт

    @Test
    public void testAmountOfNotEmptySearche()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "Linkin Park Diskography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                10
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = getAmountElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    //Конец теста и проверки ненулевого результата


    //Тест проверки пустого результата поиска

    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "lkjhgf";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                10
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line
        );

    }

    //Конец теста проверки пустого результата поиска


    //Методы

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }


    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }


    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    //Метод свайпа снизу вверх

    protected void swipeUP(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }


    //Метод быстрого свайпа

    protected void swipeUpQuick()
    {
        swipeUP(200);
    }

    //Метод свайпа и поиска элемента

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swipes = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swipes > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }

    //Метод, который свайпает элемент влево

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    //Метод, извлекающий количестов элементов в результатах поиска

    private int getAmountElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    //Метод проверяющий пустой результат поиска

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountElements(by);
        if (amount_of_elements > 0) {
            String defualt_message = "An element '" + by.toString() + "' supposed to be not present";
            throw  new AssertionError(defualt_message + " " + error_message);
        }

    }



















    //Домашняя работа к 3 занятию

    //Тест, который проверяет, что поле ввода для поиска статьи содержит текст Search Wikipedia

    @Test
    public void testInputSearchContainsText()
    {
        assertElementHasText(
                 By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "The search input doesn't contain the specified text",
                5
        );
    }

    //Метод, который проверяет наличие ожидаемого текста у элемента

    private boolean assertElementHasText(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(by, value)
        );
    }


    //Домашнее задание EX3

    //Тест, который ищет слово, убеждается, что найдено несколько статей и отменяет поиск

    @Test
    public void testCancelSearchFewResults()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "ivideon",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Can not find Ivideon",
                10
        );

        int count_search_results = countElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']")
                );

        Assert.assertTrue(
                "No results found",
                count_search_results > 0
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Search results is still present on the page",
                5
        );

    }


    //Метод, вычисляющий количество элементов. Остальные методы используются из числа существующих (листинг занятий)
    private int countElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    //Конец домашнего задания к EX3

}


