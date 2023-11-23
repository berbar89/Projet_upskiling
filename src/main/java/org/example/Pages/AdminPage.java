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
public class AdminPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "(//button[@class='oxd-button oxd-button--medium oxd-button--secondary'])[1]")
    WebElement AddButtonAdmin;
    public AdminPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }
    public AdminPage clickAddButtonAdmin() {
        wait.until(ExpectedConditions.visibilityOfAllElements(AddButtonAdmin));
        log.info("click on the Add button");
        AddButtonAdmin.click();
        return this;
    }


}
