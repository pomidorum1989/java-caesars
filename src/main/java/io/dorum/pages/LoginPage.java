package io.dorum.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By accountBtn = By.xpath("//button[@id='account-button']");
    private final By loginInput = By.xpath("//input[@data-qa='login-form-email-input']");
    private final By passwordInput = By.xpath("//input[@data-qa='login-form-password-password-input']");
    private final By loginBtn = By.xpath("//button[@data-testid='cta-login']");
    private final By errorMessage = By.xpath("//div[@class='login-content__error-header']");

    public void openCaesars() {
        openBrowserPage("https://caesarspalaceonline.com/us/mi/casino");
    }

    public void openAccount() {
        click(accountBtn);
    }

    public void fillLoginField() {
        type(loginInput, "123456");
    }

    public void fillPasswordFiled() {
        type(passwordInput, "123456");
    }

    public void clickLoginBtn() {
        click(loginBtn);
    }

    public boolean isErrorMessageVisible() {
        return isDisplayed(errorMessage);
    }
}
