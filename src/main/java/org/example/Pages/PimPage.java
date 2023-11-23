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
public class PimPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "button i.oxd-icon.bi-plus.oxd-button-icon")
    WebElement AddButton;
    @FindBy(css = "input[name='firstName']")
    WebElement firstnameinput;
    @FindBy(css = "input[name='middleName']")
    WebElement middleNameinput;
    @FindBy(css = "input[name='lastName']")
    WebElement lastnameinput;
    @FindBy(className = "oxd-switch-input")
    WebElement CreateLoginDetailsButton;
    @FindBy(xpath = "//label[text()='Username']/ancestor::div[@data-v-957b4417]/div/input")
    WebElement newUserName;
    @FindBy(xpath = "//label[text()='Password']/ancestor::div[@data-v-957b4417]/div/input")
    WebElement newpassword;
    @FindBy(xpath = "//label[text()='Confirm Password']/ancestor::div[@data-v-957b4417]/div/input")
    WebElement confirmpassword;
    @FindBy(css = "button.oxd-button--secondary")
    WebElement saveButton;

    public PimPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public PimPage clickAddButton() {
        wait.until(ExpectedConditions.visibilityOfAllElements(AddButton));
        log.info("click on the Add button");
        AddButton.click();
        return this;
    }

    public PimPage inputFirstName(String firstname) {
        wait.until(ExpectedConditions.visibilityOf(firstnameinput));
        log.info("Entering firstname");
        firstnameinput.sendKeys(firstname);
        return this;
    }

    public PimPage inputMiddleName(String middlename) {
        wait.until(ExpectedConditions.visibilityOf(middleNameinput));
        log.info("Entering middlename");
        middleNameinput.sendKeys(middlename);
        return this;
    }

    public PimPage inputLastName(String lastname) {
        wait.until(ExpectedConditions.visibilityOf(lastnameinput));
        log.info("Entering lastName");
        lastnameinput.sendKeys(lastname);
        return this;
    }

    public PimPage clickCreateLoginDetails() {
        wait.until(ExpectedConditions.visibilityOfAllElements(CreateLoginDetailsButton));
        log.info("click on the Create Login Details button");
        CreateLoginDetailsButton.click();
        return this;
    }

    public PimPage inputNewUsername(String Nusername) {
        wait.until(ExpectedConditions.visibilityOfAllElements(newUserName));
        log.info("Entering username");
        newUserName.sendKeys(Nusername);
        return this;
    }

    public PimPage inputNPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfAllElements(newpassword));
        log.info("Entering password");
        newpassword.sendKeys(password);
        return this;
    }

    public PimPage inputconfirmPassword(String Cpassword) {
        wait.until(ExpectedConditions.visibilityOfAllElements(confirmpassword));
        log.info("Entering confirm password");
        confirmpassword.sendKeys(Cpassword);
        return this;
    }

    public PimPage createUser() {
        wait.until(ExpectedConditions.visibilityOfAllElements(saveButton));
        log.info("click on the save button");
        saveButton.click();
        return this;
    }



}
