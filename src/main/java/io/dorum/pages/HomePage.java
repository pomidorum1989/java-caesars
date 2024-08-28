package io.dorum.pages;

import io.dorum.utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class HomePage extends BasePage {

    private final By adobeImg = By.xpath("//span[@class='feds-brand-label' and text()='Adobe']");

    @Step("Open page")
    public void openPage(String url) {
        openBrowserPage(url);
        log.info("{} was opened", url);
    }

    public void isAdobePageLoaded() {
        WaitUtils.waitForElementToBeVisible(adobeImg);
    }
}
