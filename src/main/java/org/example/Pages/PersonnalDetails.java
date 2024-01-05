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
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Log4j2
public class PersonnalDetails {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//label[text()='Date of Birth']/ancestor::div[@data-v-957b4417]/div/div/div/input")
    WebElement birthday;
    @FindBy(css ="input[type=\"radio\"][value=\"1\"]~span")
    WebElement genreMale;

    @FindBy(css ="input[type=\"radio\"][value=\"1\"]")
    WebElement genreMaleselect;
    @FindBy(xpath = "//label[text()='Blood Type']/ancestor::div[@data-v-957b4417]/div/div/div/div")
    WebElement blood;
    @FindBy(css = "div.oxd-select-dropdown")
    WebElement dropdownOptions;
    @FindBy(css = "div button.oxd-button[data-v-b6d78ace]")
    WebElement saveInfo;
    @FindBy(css=".oxd-button--secondary:nth-child(1)")
    WebElement saveBloodButton;


    public PersonnalDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public PersonnalDetails birth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 3)");
        wait.until(ExpectedConditions.elementToBeClickable(birthday));
        log.info("Enter Date of birth");
        birthday.sendKeys(("03-11-1989"));

        return this;
    }

    public PersonnalDetails selectGenre() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 3)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-radio-input--active")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='1']")));
        log.info("Select Genre");
        genreMale.click();
        return this;
    }

    public PersonnalDetails saveInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(saveInfo));
        saveInfo.click();
        return this;
    }

    public PersonnalDetails selectBlood() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 3)");
        // js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        wait.until(ExpectedConditions.elementToBeClickable(blood));
        log.info("Select Blood");
        blood.click();
        List<WebElement> options = dropdownOptions.findElements(By.cssSelector("div.oxd-select-option"));

        // Générez un index aléatoire
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Cliquez sur l'élément d'option correspondant à l'index généré
        options.get(randomIndex).click();
        return this;
    }

    public PersonnalDetails saveBlood() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBloodButton));
        saveBloodButton.click();
        return this;
    }

    public PersonnalDetails refrechPage() {
        driver.navigate().refresh();

        // Attendre quelques secondes pour voir le résultat
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    public PersonnalDetails verifySavedInformation() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 3)");
        wait.until(ExpectedConditions.elementToBeClickable(birthday));
        String savedDateOfBirth = birthday.getAttribute("value");
        try {
            Assert.assertEquals(savedDateOfBirth, "03-11-1989");
            log.info("Date of Birth matches the expected value.");
        } catch (AssertionError e) {
            log.error("Assertion Error: Date of Birth doesn't match the expected value.");
            throw e; // Rethrow the assertion error to mark the test as failed
        }

        //wait.until(ExpectedConditions.elementToBeClickable(genreMaleselect));
        boolean isMaleRadioButtonSelected = genreMaleselect.isSelected();
        try {
            Assert.assertTrue(isMaleRadioButtonSelected);
            log.info("The Male radio button is selected.");
        } catch (AssertionError e) {
            log.error("Error: The Male radio button is not selected.");
            throw e; // Rethrow the assertion error to mark the test as failed
        }




        //Assert.assertEquals(savedGender, "Male");
        //Assert.assertEquals(savedBloodType, getRandomBloodType());
        return this;
    }

}
