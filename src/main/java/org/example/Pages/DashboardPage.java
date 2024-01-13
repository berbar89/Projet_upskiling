package org.example.Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "a[href*='viewPimModule']")
    WebElement linkPim;
    @FindBy(css = "a[href*='viewAdminModule']")
    WebElement adminButton;
    @FindBy(css = "a[href*='viewTimeModule']")
    WebElement TimeButton;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement profilName;


    public String getNameProfil() {
        log.info("Getting Tittle");
        wait.until(ExpectedConditions.visibilityOfAllElements(profilName));
        return profilName.getText();
    }

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    public PimPage gotoPIM() {
        wait.until(ExpectedConditions.visibilityOfAllElements(linkPim));
        log.info("click on the PIM link");
        linkPim.click();
        return new PimPage(driver);
    }

    public AdminPage goToAdmin() {
        wait.until(ExpectedConditions.visibilityOfAllElements(adminButton));
        log.info("click on the Admin Button");
        adminButton.click();
        return new AdminPage(driver);
    }

    public TimePage doToTime() {
        wait.until(ExpectedConditions.visibilityOfAllElements(TimeButton));
        log.info("click on the Time Button");
        TimeButton.click();
        return new TimePage(driver);
    }


}
