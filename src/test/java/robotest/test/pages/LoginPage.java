package robotest.test.pages;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import robotest.test.base.PageBase;
import robotest.test.data.LoginData;

import java.util.List;

public class LoginPage extends PageBase {

    String URL_MATCH = "aet/login.xhtml";
    private WebDriver driver;
    private Wait<WebDriver> wait;

    @FindBy(id = "LoginForm:userid")
    private WebElement user;

    @FindBy(id = "LoginForm:password")
    private WebElement password;

    @FindBy(id = "LoginForm:language")
    private WebElement language;

    @FindBy(className = "ui-selectonemenu-item")
    private List<WebElement> languages;

    @FindBy(css = ".ui-button-text:nth-child(2)")
    private WebElement bLogin;

    @FindBy(css = "#duplicateLoginForm\\3A duplicateLoginYesBtn > .ui-button-text")
    private WebElement bConfirmLogin;

    @FindBy(css = "ul li span")
    private List<WebElement> errors;

    public LoginPage(WebDriver driver, Wait<WebDriver> wait) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
        this.wait = wait;
        this.driver = driver;
    }

    @Story("login Success")
    public MainPage loginSuccess(LoginData data){
        fillLogin(data.login);
        fillPassword(data.password);
        selectLanguage(data.language);
        save();

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".worker-avatar-clip")));
        }catch (TimeoutException e){
            Assert.fail("Timeout");
        }

        return new MainPage(driver, wait);
    }

    @Story("login Fail")
    public LoginPage loginFail(LoginData data){
        fillLogin(data.login);
        fillPassword(data.password);
        selectLanguage(data.language);
        save();

        Assert.assertNotNull(errors, "Error should be present");

        return this;
    }

    @Step("fill Login")
    private void fillLogin(String _user){
        user.click();
        user.clear();
        user.sendKeys(_user);
    }

    @Step("fill Password")
    private void fillPassword(String _password){
        password.click();
        password.clear();
        password.sendKeys(_password);
    }

    @Step("select Language")
    private void selectLanguage(String _language){
        boolean languageSelected = false;
        language.click();

        for(WebElement element:languages){
            if(element.getText().equals(_language)){
                element.click();
                languageSelected = true;
                break;
            }
        }
        driver.findElement(By.className("login-body")).click();
        if (!languageSelected){
            throw new IllegalStateException("No intended language found: " + _language);
        }
    }

    @Step("save")
    private void save(){
        bLogin.click();
        try{
            Actions builder = new Actions(driver);
            builder.moveToElement(bConfirmLogin).click().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Step("isErrorsPresent")
    public boolean isErrorsPresent(){
        return !errors.isEmpty();
    }


}