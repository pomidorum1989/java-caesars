package io.dorum.steps;

import io.cucumber.java.*;
import io.dorum.utils.WebDriverContainer;
import io.dorum.utils.WebDriverFactory;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        ThreadContext.put("threadName", String.valueOf(Thread.currentThread().threadId()));
        log.info("Scenario {} is started", scenario.getName());
        WebDriverFactory.createDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenShot("failure_" + scenario.getName());
        }
        ThreadContext.clearAll();
        WebDriverFactory.quitDriver();
        log.info("Scenario {} is finished", scenario.getName());
    }

    @Attachment(value = "{fileName}", type = "image/png", fileExtension = ".png")
    public byte[] takeScreenShot(String fileName) {
        String name = fileName.replaceAll("\\s", "");
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) WebDriverContainer.getDriver());
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(String.format(System.getProperty("user.dir") + "/target/screenshots/%s.png", name));
            FileUtils.copyFile(srcFile, destFile);
            log.info("Screenshot {} was created", destFile.getPath());
            return Files.readAllBytes(destFile.toPath());
        } catch (WebDriverException | IOException e) {
            log.error("Unable to create screenshot: {}", e.getMessage());
        }
        return null;
    }
}
