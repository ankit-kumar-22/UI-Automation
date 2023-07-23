package common.base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.*;

public class BaseScreen {
    public static WebDriver driver;
    public JavascriptExecutor jsExec;
    public WebDriverWait jsWait;
    public String separator = System.getProperty("file.separator"), currentDir = System.getProperty("user.dir");

    public BaseScreen(WebDriver driver) {
        BaseScreen.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void clearAndEnterDetails(By locator, String value) {
        driver.findElement(locator).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), value);
    }

    public void uploadFile(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    public void clearBox(By locator) {
        driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
    }

    public void sleep(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fluentWaitTillPresence(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void fluentWaitTillInvisible(By locator) {
        WebElement element = driver.findElement(locator);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchWindowException.class);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void fluentWaitTillElementClickable(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isDisplayedOnScreen(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public String getAttributeValue(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }

    public List<WebElement> getAllElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void pressEnter(By locator) {
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public void pressDownKey(By locator) {
        driver.findElement(locator).sendKeys(Keys.DOWN);
    }

    public void waitOnlyFewSeconds(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilJSReady() {
        try {
            jsWait = new WebDriverWait(driver, 40);
            jsExec = (JavascriptExecutor) driver;
            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) this.driver)
                    .executeScript("return document.readyState").toString().equals("complete");
            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");
            if (!jsReady) {
                jsWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitForJQueryLoad() {
        try {
            jsWait = new WebDriverWait(driver, 40);
            jsExec = (JavascriptExecutor) driver;
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) this.driver)
                    .executeScript("return jQuery.active") == 0);
            boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");
            if (!jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void ajaxComplete() {
        jsExec.executeScript("var callback = arguments[arguments.length - 1];"
                + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    public void waitForAnimationToComplete(String css) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = this.driver.findElements(By.cssSelector(css)).size();
            return loadingElements == 0;
        };
        jsWait.until(angularLoad);
    }

    public void fluentWaitAfterLoading(By locators) {
        fluentWait(locators);
        waitUntilJSReady();
        waitForJQueryLoad();
    }

    public void waitForLoader() {
        waitUntilJSReady();
        waitForJQueryLoad();
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        sleep(2);
    }

    public void scrollHorizontal() {
        ((JavascriptExecutor)
                driver).executeScript("window.scrollBy(2000,0)");
    }

    public void tapOnElement(By locator) {
        WebElement element = driver.findElement(locator);
        TouchActions actionOne = new TouchActions(driver);
        actionOne.doubleClick(element);
        actionOne.release();
    }

    public String switchToNewWindow() {
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
            if (!parentWindowHandler.equals(subWindowHandler)) {
                driver.switchTo().window(subWindowHandler);
                break;
            }
        }
        return parentWindowHandler;// switch back to parent window
    }

    public void selectCurrentWindow(String currentWindow) {
        driver.switchTo().window(currentWindow);
    }

    public void selectValuesFromDropDown(String valueToSelect, By locator) {
        Select drpCountry = new Select(driver.findElement(locator));
        drpCountry.selectByVisibleText(valueToSelect);
    }

    protected static String generateRandomString(int length) {
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZacbdefghijklmnopqrstuwxyz";
        Random rnd = new Random();
        StringBuilder randomString = new StringBuilder(Alphabet.length());
        for (int i = 0; i < length; i++)
            randomString.append(Alphabet.charAt(rnd.nextInt(Alphabet.length())));
        return randomString.toString();
    }

    protected static int generateRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    protected static long generateRandomNumber(long min, long max) {
        return (long) (Math.random() * (max - min + 1) + min);
    }

    protected static Long generateRandomPhoneNumber() {
        return (long) Math.floor(Math.random() * 900000000L) + 9000000000L;
    }

    protected static String generateRandomEmailId(int length) {
        return generateRandomString(length) + generateRandomInt(10, 100000) + "@test.com";
    }

    public static String printCurrentDate(String format, int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days); // I just want date before 90 days. you can give that you want.
        SimpleDateFormat s = new SimpleDateFormat(format); // you can specify your format here...
        return s.format(new Date(cal.getTimeInMillis()));
    }
}


