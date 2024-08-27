package io.dorum.pages;

import io.dorum.utils.WebDriverContainer;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;

@Log4j2
public class BasePage {

    public String getPageTitle() {
        return WebDriverContainer.getDriver().getTitle();
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