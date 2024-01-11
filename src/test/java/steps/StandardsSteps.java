package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StandardsSteps {
    WebDriver driver;
    private void init() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();

    }

    public WebDriver getDriver() {
        return driver;
    }

    @Given("je vais sur la page web {string}")
    public void JevaissurlapagesOrangeHRM(String url) {
        init();
        driver.get(url);

    }

    @After
    public void teamDown() {
        driver.quit();
        System.out.println("Teardown successful !");
    }
}
