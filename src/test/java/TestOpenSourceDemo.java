import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.example.Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

@Log4j2
public class TestOpenSourceDemo {
    Faker faker = new Faker();
    String usernameA = faker.name().username();
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
    /*@Test
   public void testCreationApim() {

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
                .createUser();


    }*/

      @Test
      public void testCreateAdmin(){
          LoginPage lp = new LoginPage(driver);
          lp.inputUserName(username)
                  .inputPassword(password)
                  .clickLogin()
                  .goToAdmin()
                  .searchEmployee()
                  .ModifyEmployee()
                  .selectAdminOption()
                  .clickSaveButtom()
                  .logout();


      }
   /* @Test
    public void testRemplirFormulaire() {
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
    public void testFeuilleTempProjet(){
        LoginPage lp = new LoginPage(driver);
        lp.inputUserName(username)
                .inputPassword(password)
                .clickLogin()
                .doToTime();
    }*/

  /*  @AfterMethod
    public void Teardown() {
        log.info("Finishing test");
        driver.quit();
    }*/

}
