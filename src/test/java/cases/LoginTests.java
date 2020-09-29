package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import parsers.ConfigParser;
import base.TestBase;
import pages.LoginPage;
import pages.MainPage;

public class LoginTests extends TestBase {


    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong login)")
    @Test (priority=1)
    public void loginNegativeWrongLogin() {
        goToLogin();
        LoginPage loginPage = new LoginPage();
        loginPage = loginPage.loginFail(ConfigParser.login + "1", ConfigParser.password);
        Assert.assertTrue(loginPage.isErrorsPresent());
    }


    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong password)")
    @Test (priority=1)
    public void loginNegativeWrongPassword() {
        goToLogin();
        LoginPage loginPage = new LoginPage();
        loginPage = loginPage.loginFail(ConfigParser.login, ConfigParser.password + "1");
        Assert.assertTrue(loginPage.isErrorsPresent());

    }


    @Severity(SeverityLevel.BLOCKER)
    @Description("positive Login")
    @Test (priority=2)
    public void loginPositive() {
        goToLogin();
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.loginSuccess(ConfigParser.login, ConfigParser.password);
        Assert.assertTrue(mainPage.isOpen());
    }
}
