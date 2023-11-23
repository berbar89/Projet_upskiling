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
public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css="a[href*='viewPimModule']")
    WebElement linkPim;
    @FindBy(css="a[href*='viewPimModule']")
    WebElement adminButton;

    public DashboardPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    public PimPage gotoPIM(){
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
}
