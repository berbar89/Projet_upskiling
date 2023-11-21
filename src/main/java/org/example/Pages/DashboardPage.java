package org.example.Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

@Log4j2
public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css="a[href*='viewPimModule']")
    WebElement linkPim;
    public HomePage (WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    public HomePage gotoPIM(){
        wait.until(ExpectedConditions.visibilityOfAllElements(linkPim));
        log.info("click on the PIM link");
        linkPim.click();
        return this;
    }
}
