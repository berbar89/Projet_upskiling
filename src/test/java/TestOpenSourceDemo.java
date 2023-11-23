import lombok.extern.log4j.Log4j2;
import org.example.Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

@Log4j2
public class TestOpenSourceDemo {
    WebDriver driver;
    String username = "admin";
    String password = "admin123";
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        log.info("Navigated to the URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.get(URL);
    }

    @Test
    public void creationApim() {

        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .gotoPIM()
                .clickAddButton()
                .inputFirstName("Nassima")
                .inputMiddleName("Nass")
                .inputLastName("AAA")
                .clickCreateLoginDetails()
                .inputNewUsername("Ayden.ikken")
                .inputNPassword("Aydennnn1")
                .inputconfirmPassword("Aydennnn1")
                .createUser();


    }
    @Test
    public void createAdmin(){
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .goToAdmin();


    }
    @AfterMethod
    public void Teardown() {
        log.info("Finishing test");
        driver.quit();
    }

}
