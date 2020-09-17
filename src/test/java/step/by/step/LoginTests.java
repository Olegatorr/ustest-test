package step.by.step;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong login)")
    @Test (priority=1)
    public void loginNegativeWrongLogin() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("2421525dfs", "ELECTROSTALIN", "English");
        loginPage.loginFail(data);

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("negative Login (wrong password)")
    @Test (priority=1)
    public void loginNegativeWrongPassword() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("ROBOTESTER", "sdaf54636", "English");
        loginPage.loginFail(data);

    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("positive Login")
    @Test (priority=2)
    public void loginPositive() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("ROBOTESTER", "ELECTROSTALIN", "English");
        loginPage.loginSuccess(data);

    }
}
