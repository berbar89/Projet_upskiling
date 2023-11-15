import lombok.extern.log4j.Log4j2;
import org.example.Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class TestOpenSourceDemo {
    WebDriver driver;
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        log.info("Navigated to the URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.get(URL);
    }

    @Test
    public void testOpensourcedemo() {
        String username = "admin";
        String password = "admin123";
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin();


    }

    @AfterMethod
    public void Teardown() {
        log.info("Finishing test");
        driver.quit();
    }

}
