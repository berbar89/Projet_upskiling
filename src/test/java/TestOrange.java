import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.example.Pages.LoginPage;
import org.example.Pages.PersonnalDetails;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.ParseException;


@Log4j2

public class TestOrange {
    Faker faker = new Faker();
    String usernameA = faker.name().username();
    WebDriver driver;
    String username = "admin";
    String password = "admin123";
    public static final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private ExtentReports extent;
    private ExtentTest extentTest;
    JSONParser parser;
    JSONObject testData;
    String gender="M";


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
    public void testCreationApim() {
        extentTest = extent.createTest("cas de test: cration PIM", "");
        //test.pass("Step 1: Opened the browser");
        String expectedTitle = "Personal Details";
        LoginPage lp = new LoginPage(driver);
        String ActualTitle = lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .gotoPIM()
                .clickAddButton()
                .inputFirstName("Nassima")
                .inputMiddleName("Nas")
                .inputLastName("berbar")
                .clickCreateLoginDetails()
                .inputNewUsername("ikk.nassima")
                .inputNPassword("Aydennnn1n")
                .inputconfirmPassword("Aydennnn1n")
                .createUser()
                .getTitle();

        Assert.assertEquals(ActualTitle, expectedTitle);


    }


    @Test(priority = 2)
    public void testCreateAdmin() {
        extentTest = extent.createTest("cas de test: cration admin", "");
        LoginPage lp = new LoginPage(driver);
        String profilNameActual = lp.inputUserName(username)
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
                .clickLogin()
                .getNameProfil();
        Assert.assertEquals(profilNameActual, "Nassima berbar");

    }

    @Test(priority = 3)
    public void testRemplirFormulaire() {
        extentTest = extent.createTest("cas de test: Remplissage de formulaire", "");
        LoginPage lp = new LoginPage(driver);
        PersonnalDetails EmployeeDetails= lp.inputUserName(username)
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
                .birth("1989-11-03")
                .selectGenre(gender)
                .saveInfo()
                .selectBlood()
                .saveBlood()
                .refrechPage();
        try {
            Assert.assertEquals(EmployeeDetails.getBirtdaydate(), "1989-11-03");
            log.info("Date of Birth matches the expected value.");
        } catch (AssertionError e) {
            log.error("Assertion Error: Date of Birth doesn't match the expected value.");
            throw e; // Rethrow the assertion error to mark the test as failed
        }
        if(gender.equals("M")) {
            try {
                Assert.assertTrue(EmployeeDetails.isMaleSelected());
                log.info("The Male radio button is selected.");
            } catch (AssertionError e) {
                log.error("Error: The Male radio button is not selected.");
                throw e; // Rethrow the assertion error to mark the test as failed
            }
        }
            else {
                try {
                    Assert.assertTrue(EmployeeDetails.isFemalealeSelected());
                    log.info("The Female radio button is selected.");
                } catch (AssertionError e) {
                    log.error("Error: The Female radio button is not selected.");
                    throw e; // Rethrow the assertion error to mark the test as failed
                }

            }
            Assert.assertNotNull(EmployeeDetails.getBloodValue());

    }

    @Test(priority = 4)

    public void testFeuilleTempProjet() {
        extentTest = extent.createTest("cas de test: Feuille de temps du projet ", "");
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .doToTime()
                .clickReport()
                .goToProjectReport()
                .selectProject();
        //.clickViewButtom();
    }

    @AfterMethod
    public void captureScreen(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String name = "screenshot.png";
// Now you can do whatever you need to do with it, for example copy somewhere
            try {
                FileUtils.copyFile(scrFile, new File("test-output/screenshots/" + name));
            } catch (IOException e) {
                log.error("screenshot failed");
                throw new RuntimeException(e);
            }
        }
    }

    /*  @AfterMethod
      public void TeardownM() {
          log.info("Finishing test");
          driver.quit();
      }*/
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
        //driver.quit();

        // Finir le rapport
        extent.flush();
    }

}
