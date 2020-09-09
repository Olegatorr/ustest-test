package step.by.step;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login extends TestBase{

    @Test
    public void login() {
        goToMainPage();
        Assert.assertTrue(driver.getCurrentUrl().contains("aet/private/main.xhtml"));
    }
}