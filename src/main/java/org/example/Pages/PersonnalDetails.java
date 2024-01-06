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
    //@FindBy(css = ".oxd-topbar-header-title")
    @FindBy(css = "h6[data-v-7b563373][data-v-b6d78ace]")
    WebElement title;
    @FindBy(xpath = "//label[text()='Date of Birth']/ancestor::div[@data-v-957b4417]/div/div/div/input")
    WebElement birthday;
    @FindBy(css = "input[type=\"radio\"][value=\"1\"]~span")
    WebElement genreMale;
    @FindBy(css = "input[type=\"radio\"][value=\"2\"]~span")
    WebElement genreFemale;

    @FindBy(css = "input[type=\"radio\"][value=\"1\"]")
    WebElement genreMaleselect;
    @FindBy(css = "input[type=\"radio\"][value=\"2\"]")
    WebElement genreFemaleselect;
    @FindBy(xpath = "//label[text()='Blood Type']/ancestor::div[@data-v-957b4417]/div/div/div/div")
    WebElement blood;
    @FindBy(css = "div.oxd-select-dropdown")
    WebElement dropdownOptions;
    @FindBy(css = "div button.oxd-button[data-v-b6d78ace]")
    WebElement saveInfo;
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement saveBloodButton;


    public PersonnalDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        log.info("Getting Tittle");
        return title.getText();
    }

    public PersonnalDetails birth(String birthdate) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 3)");
        wait.until(ExpectedConditions.elementToBeClickable(birthday));
        log.info("Enter Date of birth");
        birthday.sendKeys(birthdate);
        log.info("Date of Birth filled: " + birthdate);
        return this;
    }

    public PersonnalDetails selectGenre(String gender) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 3)");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-radio-input--active")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='1']")));
        wait.until(ExpectedConditions.visibilityOfAllElements(genreMale));
        wait.until(ExpectedConditions.visibilityOfAllElements(genreFemale));
        log.info("Select Genre");
        if (gender.equals("M")) {
            genreMale.click();
            log.info("Gender selected: Male");
            return this;
        } else if (gender.equals("F")) {
            genreFemale.click();
            log.info("Gender selected: Female");
            return this;
        } else {
            log.error("Gender not defined correctly ");
            return this;
        }

    }

    public PersonnalDetails saveInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(saveInfo));
        saveInfo.click();
        log.info("birthday and gender saved successfully");
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
        log.info("Blood selected");
        return this;
    }

    public PersonnalDetails saveBlood() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBloodButton));
        saveBloodButton.click();
        log.info("Blood saved successfully");
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

    public String getBirtdaydate() {
        log.info("Getting Birth Date...");
        return birthday.getAttribute("value");
    }

    public Boolean isMaleSelected() {
        log.info("Checking if the user man is selected...");
        return genreMaleselect.isSelected();
    }

    public Boolean isFemalealeSelected() {
        log.info("Checking if the user Female is selected...");
        return genreFemaleselect.isSelected();
    }

    public String getBloodValue() {
        log.info(" getting blood value ");
        return blood.getText();
    }


}
