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
public class TimePage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//span[contains(text(),'Reports')]")
    WebElement reportButtom;
    @FindBy(xpath = "//a[text()='Project Reports']")
    WebElement reportProjectButtom;

    public TimePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public TimePage clickReport() {
        wait.until(ExpectedConditions.visibilityOf(reportButtom));
        reportButtom.click();
        return this;
    }

    public ReportProject goToProjectReport() {
        wait.until(ExpectedConditions.visibilityOf(reportProjectButtom));
        reportProjectButtom.click();
        return new ReportProject(driver);

    }

}
