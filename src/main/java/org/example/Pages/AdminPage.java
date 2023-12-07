package org.example.Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    @FindBy(xpath = "//label[text()='Username']/ancestor::div[@data-v-957b4417]/div/input")
    WebElement userNameField;
    @FindBy(className = "bi-pencil-fill")
    WebElement modifybuttom ;
    @FindBy(xpath = "//label[text()= 'User Role']/ancestor::div[@data-v-957b4417]/div/div")
    WebElement dropdown;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    WebElement saveButtom;
    @FindBy(className = "orangehrm-left-space")
    WebElement profilButtom;
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public AdminPage clickAddButtonAdmin() {
        wait.until(ExpectedConditions.visibilityOfAllElements(AddButtonAdmin));
        log.info("click on the Add button");
        AddButtonAdmin.click();
        return this;
    }

    public AdminPage searchEmployee() {
        String searchText = "ikk.nassima";
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        log.info("Entering search text: {}", searchText);
        userNameField.sendKeys(searchText);
        userNameField.sendKeys(Keys.RETURN);
        return this;
    }
    public AdminPage ModifyEmployee(){
        wait.until(ExpectedConditions.visibilityOf(modifybuttom));
        modifybuttom.click();
        return this;
    }
    public AdminPage selectAdminOption(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        log.info("click on user Roles");
        dropdown.click();
        WebElement adminOption = driver.findElement(By.xpath("//div[@class='oxd-select-option']//span[text()='Admin']"));
        log.info("Clicked on the 'Admin' option");
        adminOption.click();

        return this;
    }
    public AdminPage clickSaveButtom(){
        wait.until(ExpectedConditions.visibilityOf(saveButtom));
        log.info("Clicked on the 'Save' button");
        saveButtom.click();
        return this;
    }

    public LoginPage logout(){
        wait.until(ExpectedConditions.visibilityOf(profilButtom));
        log.info("click on profil buttom");
        profilButtom.click();
        return new LoginPage(driver);

    }

}