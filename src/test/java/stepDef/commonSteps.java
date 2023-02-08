package stepDef;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Homepage;

import java.util.concurrent.TimeUnit;

public class commonSteps {
    WebDriver driver;
    private String username;
    private String password;
    WebDriverWait wait;
    Homepage homepage;


    @Before
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver-1.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("^open the (.*)$")
    public void openTheUrl(String url) {
        driver.get(url);
    }

    @And("^click register button$")
    public void clickRegisterButton() {
        Homepage homepage = new Homepage(driver);
        homepage.clickRegisterButton();
    }

    @And("^enter email as \"([^\"]*)\" password as \"([^\"]*)\" and click register$")
    public void enterEmailAsPasswordAsAndClickRegister(String email, String password) {
        Homepage homepage = new Homepage(driver);
        homepage.enterRegistrationEmail(email);
        homepage.enterPassword(password);
        homepage.enterConfirmPassword(password);
        homepage.clickRegisterSubmitButton();
        username = email;
        this.password = password;

    }

    @And("^login with the same credentials$")
    public void loginWithTheSameCredentials() {
        Homepage homepage = new Homepage(driver);
        homepage.enterUsername(username);
        homepage.enterPassword(password);
        homepage.clickLoginButton();
    }

    @Then("^the page is redirected to homepage$")
    public void thePageIsRedirectedToHomepage() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".vue-avatar--wrapper")).isDisplayed());
    }

    @And("^logout of the application$")
    public void logoutOfTheApplication() {
        driver.findElement(By.cssSelector(".vue-avatar--wrapper")).click();
        driver.findElement(By.xpath("//button[text()='Logout']")).click();
    }

    @When("^enter random email and password and click register$")
    public void enterRandomEmailAndPasswordAndClickRegister() {
        Homepage homepage = new Homepage(driver);
        username = RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
        password = RandomStringUtils.randomAlphanumeric(5);
        homepage.enterRegistrationEmail(username);
        homepage.enterPassword(password);
        homepage.enterConfirmPassword(password);
        homepage.clickRegisterSubmitButton();
    }

    @Then("^email already exists error message is displayed$")
    public void emailAlreadyExistsErrorMessageIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Email already exists.']")).isDisplayed());
    }

    @Then("^\"([^\"]*)\" error message is displayed$")
    public void errorMessageIsDisplayed(String errorMessage) {
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement
//                (By.xpath("//*[contains(normalize-space(),'" + errorMessage + "')]"))));
        String error = wait.until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//span[contains(@class,'alert')]")))).getText();
        Assert.assertTrue(error.contains(errorMessage));

    }

    @When("^enter \"([^\"]*)\" only$")
    public void enterEmailOnly(String value) {
        Homepage homepage = new Homepage(driver);
        if (value.equalsIgnoreCase("email")) {
            username = RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
            homepage.enterRegistrationEmail(username);
        } else if (value.equalsIgnoreCase("password")) {
            password = RandomStringUtils.randomAlphanumeric(5);
            homepage.enterPassword(password);
        } else if (value.contains("email") && value.contains("password")) {
            username = RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
            password = RandomStringUtils.randomAlphanumeric(5);
            homepage.enterRegistrationEmail(username);
            homepage.enterPassword(password);
        }
        homepage.clickRegisterSubmitButton();
    }

    @And("^enter the registered email and password and click login$")
    public void enterTheRegisteredEmailAndPasswordAndClickLogin() {
        homepage = new Homepage(driver);
        homepage.enterUsername(username);
        homepage.enterPassword(password);
        homepage.clickLoginButton();
    }
}
