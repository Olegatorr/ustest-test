package robotest.test.cases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import robotest.test.base.TestBase;
import robotest.test.data.LoginData;
import robotest.test.pages.LoginPage;
import robotest.test.pages.MainPage;

public class LoginTests extends TestBase {

    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong login)")
    @Test (priority=1)
    public void loginNegativeWrongLogin() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("2421525dfs", "ELECTROSTALIN", "English");
        loginPage.loginFail(data);
        Assert.assertTrue(loginPage.isErrorsPresent(), "Checking if error is present");

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong password)")
    @Test (priority=1)
    public void loginNegativeWrongPassword() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("ROBOTESTER", "sASD54636", "English");
        loginPage.loginFail(data);
        Assert.assertTrue(loginPage.isErrorsPresent(), "Checking if error is present");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("positive Login")
    @Test (priority=2)
    public void loginPositive() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("ROBOTESTER", "ELECTROSTALIN", "English");
        MainPage mainPage = loginPage.loginSuccess(data);
        Assert.assertNotNull(mainPage, "Checking if main page obj initialized");

    }
}
