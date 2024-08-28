package io.dorum.pages;

import io.dorum.utils.WaitUtils;
import io.dorum.utils.WebDriverContainer;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Log4j2
public class BasePage {

    public void openBrowserPage(String url) {
        WebDriverContainer.getDriver().get(url);
        WaitUtils.waitForUrlToContain(url);
        waitForPageToLoad(10);
    }

    public String getPageTitle() {
        return WebDriverContainer.getDriver().getTitle();
    }

    public void click(By by) {
        WaitUtils.waitForElementToBeClickable(by).click();
    }

    public void type(By by, String text) {
        WebDriverContainer.getDriver().findElement(by).sendKeys(text);
    }

    public boolean isDisplayed(By by) {
        return WebDriverContainer.getDriver().findElement(by).isDisplayed();
    }

    public void dragAndDrop() {
        WebElement sourceElement = WebDriverContainer.getDriver().findElement(By.id("source"));
        WebElement targetElement = WebDriverContainer.getDriver().findElement(By.id("target"));
        Actions actions = new Actions(WebDriverContainer.getDriver());
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    public void waitForPageToLoad(int seconds) {
        boolean pageLoaded = false;
        int attempts = 0;
        while (!pageLoaded && attempts < seconds) {
            pageLoaded = ((JavascriptExecutor) WebDriverContainer.getDriver()).executeScript("return document.readyState").equals("complete");
            if (!pageLoaded) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn("Page still loading");
                }
            }
            attempts++;
        }
        if (pageLoaded) {
            log.info("Page is fully loaded in {} second(s)", attempts);
        } else {
            log.info("Page may not have fully loaded after {} second(s)", seconds);
        }
    }

}