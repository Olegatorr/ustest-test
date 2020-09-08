package step.by.step;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends TestBase{

    @Test
    public void testLogin() {
        goToMainPage();
        Assert.assertTrue(driver.getCurrentUrl().contains("aet/private/main.xhtml"));
    }
}