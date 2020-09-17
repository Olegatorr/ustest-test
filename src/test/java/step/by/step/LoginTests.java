package step.by.step;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Severity(SeverityLevel.BLOCKER)
    @Description("negative Login")
    @Test
    public void negativeLogin() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("2421525dfs", "sdaf54636", "English");
        loginPage.loginFail(data);

    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("positive Login")
    @Test
    public void positiveLogin() {

        goToLogin();
        LoginPage loginPage = new LoginPage(driver, wait);
        LoginData data = new LoginData("ROBOTESTER", "ELECTROSTALIN", "English");
        loginPage.loginSuccess(data);

    }
}
