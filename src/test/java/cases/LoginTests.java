package cases;

import helpers.LoginData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import parsers.ConfigParser;
import base.TestBase;
import pages.LoginPage;
import pages.MainPage;


/**
 *  Test class for Login page
 *
 *  Example loginNegativeWrongLogin():
 *
 *  goToLogin()
 *  open Login page
 *
 *  LoginPage loginPage = new LoginPage()
 *  create new loginPage page object
 *
 *  LoginData data = new LoginData()
 *  create new LoginData object (contains String for all the fields)
 *
 *  loginPage = loginPage.loginFail(data);
 *  call loginFail method of loginPage page object
 *  fill the fields using LoginData and submit login request
 *  since login is expected to fail, loginPage object is returned
 *
 *  Assert.assertTrue(loginPage.isErrorsPresent());
 *  check if any errors us present on the page
 *  since this test is expected to fail, error(s) should be present
 *
 */
public class LoginTests extends TestBase {

    /**
     *  Negative test of login
     *  Login is expected to fail
     *  because Login is wrong
     */
    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong login)")
    @Test (priority=1)
    public void loginNegativeWrongLogin() {
        goToLogin();
        LoginPage loginPage = new LoginPage();
        LoginData data = new LoginData(ConfigParser.login + "1", ConfigParser.password);
        loginPage = loginPage.loginFail(data);
        Assert.assertTrue(loginPage.isErrorsPresent());
    }

    /**
     *  Negative test of login
     *  Login is expected to fail
     *  because Password is wrong
     */
    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong password)")
    @Test (priority=1)
    public void loginNegativeWrongPassword() {
        goToLogin();
        LoginPage loginPage = new LoginPage();
        LoginData data = new LoginData(ConfigParser.login, ConfigParser.password + "1");
        loginPage = loginPage.loginFail(data);
        Assert.assertTrue(loginPage.isErrorsPresent());
    }

    /**
     *  Positive test of login
     *  Login is expected to succeed
     */
    @Severity(SeverityLevel.BLOCKER)
    @Description("positive Login")
    @Test (priority=2)
    public void loginPositive() {
        goToLogin();
        LoginPage loginPage = new LoginPage();
        LoginData data = new LoginData(ConfigParser.login, ConfigParser.password);
        MainPage mainPage = loginPage.loginSuccess(data);
        Assert.assertTrue(mainPage.isOpen());
    }
}
