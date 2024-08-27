package io.dorum.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.Synchronized;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

@Log4j2
public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    @Synchronized
    @Step("Driver creation")
    public synchronized static void createDriver() {
        WebDriverManager webDriverManager;
        if (Objects.isNull(DRIVER_THREAD_LOCAL.get())) {
            String browser = System.getProperty("browser");
            switch (browser) {
                case "chrome_docker":
                    createDockerizedBrowser(WebDriverManager.chromedriver());
                    break;
                case "firefox_docker":
                    createDockerizedBrowser(WebDriverManager.firefoxdriver());
                    break;
                case "chrome":
                    webDriverManager = WebDriverManager.chromedriver().cachePath("./target/driver/" + Thread.currentThread().threadId());
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--lang=en-US");
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--incognito");
                    DRIVER_THREAD_LOCAL.set(webDriverManager.capabilities(chromeOptions).create());
                    WebDriverContainer.setDriver(DRIVER_THREAD_LOCAL.get());
                    log.info("Chrome driver location {}", webDriverManager.getDownloadedDriverPath());
                    log.info("Web driver was created with Thread ID: {}, Session ID: {}, Window handle: {}",
                            Thread.currentThread().threadId(), ((RemoteWebDriver) WebDriverContainer.getDriver()).getSessionId(),
                            DRIVER_THREAD_LOCAL.get().getWindowHandle());
                    break;
                case "firefox":
                    webDriverManager = WebDriverManager.firefoxdriver();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("intl.accept_languages", "en-US");
                    firefoxOptions.setProfile(profile);
                    firefoxOptions.addArguments("--headless");
                    WebDriver webDriver = webDriverManager.capabilities(firefoxOptions).create();
                    DRIVER_THREAD_LOCAL.set(webDriver);
                    log.info("FireFox driver location {}", webDriverManager.getDownloadedDriverPath());
                    break;
                case "edge":
                    webDriverManager = WebDriverManager.edgedriver();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("acceptInsecureCerts", true);
                    edgeOptions.setCapability("headless", false);
                    DRIVER_THREAD_LOCAL.set(webDriverManager.capabilities(edgeOptions).create());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }
    }

    @Step("Driver quit")
    public synchronized static void quitDriver() {
        if (Objects.nonNull(DRIVER_THREAD_LOCAL.get())) {
            log.info("Web driver was removed with Thread ID: {}, Session ID: {}",
                    Thread.currentThread().threadId(), ((RemoteWebDriver) WebDriverContainer.getDriver()).getSessionId());
            WebDriverContainer.getDriver().quit();
            DRIVER_THREAD_LOCAL.remove();
            WebDriverContainer.removeDriver();
        }
    }

    public synchronized static void createDockerizedBrowser(WebDriverManager webDriverManagerInstance) {
        WebDriverManager webDriverManager;
        if (WebDriverManager.isDockerAvailable()) {
            webDriverManager = webDriverManagerInstance.browserInDocker();
            webDriverManager.dockerDefaultArgs("--disable-gpu,--no-sandbox,--lang=en-US,--disable-gpu,--incognito");
            DRIVER_THREAD_LOCAL.set(webDriverManager.create());
            WebDriverContainer.setDriver(DRIVER_THREAD_LOCAL.get());
            log.info("Browser container id: {}, docker server url:{}",
                    webDriverManager.getDockerBrowserContainerId(), webDriverManager.getDockerSeleniumServerUrl());
        } else {
            log.error("You need to start the Docker app");
        }
    }
}