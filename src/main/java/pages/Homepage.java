package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {

    WebDriver driver;
    By loginRegisterButton = By.xpath("//a[text()='Register']");
    By usernameTextbox = By.id("username");
    By passwordTextbox = By.id("password");
    By loginButton = By.xpath("//*[@name='login']");
    By registerEmailTextBox = By.id("email");
    By registerConfirmPasswordTextBox = By.id("password-confirm");
    By registerSubmit = By.xpath("//*[@type='submit']");
    public Homepage(WebDriver driver){
        this.driver = driver;
    }

    public void clickRegisterButton(){
        driver.findElement(loginRegisterButton).click();
    }

    public void enterUsername(String username){
        driver.findElement(usernameTextbox).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordTextbox).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void enterRegistrationEmail(String email){
        driver.findElement(registerEmailTextBox).sendKeys(email);
    }

    public void enterConfirmPassword(String password){
        driver.findElement(registerConfirmPasswordTextBox).sendKeys(password);
    }

    public void clickRegisterSubmitButton(){
        driver.findElement(registerSubmit).click();
    }

}
