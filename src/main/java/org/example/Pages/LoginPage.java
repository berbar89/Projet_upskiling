package org.example.Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "div[data-v-957b4417] input[name='username']")
    WebElement usernameInput;
    @FindBy(css="div[data-v-957b4417] input[name='password']")
    WebElement passwordInput;
    @FindBy(className = "oxd-button")
    WebElement loginBotton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public LoginPage inputUserName(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        log.info("Entering username" );
        usernameInput.sendKeys(username);
        return this;
    }
    public LoginPage inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        log.info("Entering password");
        passwordInput.sendKeys(password);
        return this;
    }
    public DashboardPage clickLogin(){
        log.info("Clicking on login button");
        loginBotton.click();
        return new DashboardPage(driver);
    }
}
