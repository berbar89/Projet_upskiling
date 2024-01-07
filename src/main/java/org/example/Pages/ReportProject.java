package org.example.Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class ReportProject {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "input[placeholder='Type for hints...']")
    WebElement projectName;
    List<String> projects = Arrays.asList("ACME", "COCA", "APACHE", "FRESH BOOKS");
    @FindBy(className = "oxd-button")
    WebElement viewButtom;
    @FindBy(css = "div.oxd-report-table-footer span.oxd-text--footer")
    WebElement totalduration;
    double sum;

    public ReportProject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public ReportProject selectProject() {
        // Attendre quelques secondes pour voir le résultat
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOf(projectName));

        for (String Element : projects) {
            projectName.sendKeys(Element);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement premierElement = driver.findElement(By.cssSelector("div.oxd-autocomplete-dropdown div"));
            log.info("select project: " + Element);
            premierElement.click();
            log.info("click on view buttom");
            wait.until(ExpectedConditions.visibilityOf(viewButtom));
            viewButtom.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait.until(ExpectedConditions.visibilityOfAllElements(totalduration));
            String totalDuration = totalduration.getText();
            //replaceAll("[^\\d.]", "") est utilisée pour extraire uniquement les chiffres et le point décimal de la chaîne
            String durationValue = totalDuration.replaceAll("[^\\d.]", "");
            try {
                Assert.assertEquals(sumTimesValues(), Double.parseDouble(durationValue));
                log.info("Total Duration (Hours) matches the sum Times Values");
            } catch (AssertionError e) {
                log.error("Assertion Error: Total Duration (Hours) doesn't match the esum Times Values");
                throw e; // Rethrow the assertion error to mark the test as failed
            }
            driver.navigate().refresh();
            // Attendre quelques secondes pour voir le résultat
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return this;
    }

    public double sumTimesValues() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-alt")));
        List<WebElement> timeElements = driver.findElements(By.cssSelector(".col-alt"));
        sum = 0.00;
        for (WebElement timeElement : timeElements) {
            double timeValue = Double.parseDouble(timeElement.getText());
            sum += timeValue;
        }
        return sum;
    }
}
