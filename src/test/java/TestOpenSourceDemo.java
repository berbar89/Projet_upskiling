import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.config.Order;
import org.example.Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.*;


@Log4j2

public class TestOpenSourceDemo {
    Faker faker = new Faker();
    String usernameA = faker.name().username();
    WebDriver driver;
    String username = "admin";
    String password = "admin123";
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private ExtentReports extent;
    private ExtentTest extentTest;

    @BeforeTest
    public void setUp() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setUpM() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        // Configurez les options pour le mode headless
       /* FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        // Instanciez le WebDriver avec les options
        driver = new FirefoxDriver(options);*/
        driver = new FirefoxDriver();
        log.info("Navigated to the URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.get(URL);
    }

    @Test(priority = 1)
    private void testCreationApim() {
        extentTest = extent.createTest("cas de test: cration PIM", "");
        //test.pass("Step 1: Opened the browser");
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .gotoPIM()
                .clickAddButton()
                .inputFirstName("Nassim")
                .inputMiddleName("Nas")
                .inputLastName("AAAkk")
                .clickCreateLoginDetails()
                .inputNewUsername("ikk.nassima")
                .inputNPassword("Aydennnn1n")
                .inputconfirmPassword("Aydennnn1n")
                .createUser();


    }


    @Test(priority = 2)
    public void testCreateAdmin() {
        extentTest = extent.createTest("cas de test: cration admin", "");
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .goToAdmin()
                .searchEmployee()
                .ModifyEmployee()
                .selectAdminOption()
                .clickSaveButtom()
                .clickProfil()
                .logout()
                .inputUserName("ikk.nassima")
                .inputPassword("Aydennnn1n")
                .clickLogin();

    }

    @Test(priority = 3)
    public void testRemplirFormulaire() {
        extentTest = extent.createTest("cas de test: Remplissage de formulaire", "");
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .gotoPIM()
                .clickAddButton()
                .inputFirstName("Nassim")
                .inputMiddleName("Nas")
                .inputLastName("AAAkk")
                .clickCreateLoginDetails()
                .inputNewUsername(usernameA)
                .inputNPassword("Aydennnn1n")
                .inputconfirmPassword("Aydennnn1n")
                .createUser()
                .birth()
                .selectGenre()
                .selectBlood()
                .refrechPage();

    }

    @Test
   /* public void testFeuilleTempProjet() {
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .doToTime();
    }
*/
  /*  @AfterMethod
    public void TeardownM() {
        log.info("Finishing test");
        driver.quit();
    }
*/
    @AfterMethod

    public void tearDownwM(ITestResult result) {
        // Gérer le statut du test (réussite/échec)
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Échec du test", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test réussi", ExtentColor.GREEN));
        } else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test ignoré", ExtentColor.YELLOW));
            extentTest.skip(result.getThrowable());
        }

        // Fermer le navigateur
         driver.quit();

        // Finir le rapport
        extent.flush();
    }

}
