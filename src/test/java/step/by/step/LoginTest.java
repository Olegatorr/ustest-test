package step.by.step;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends TestBase{

    @Test
    public void test() {

        goToMainPage();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".worker-avatar-clip")));

        if(driver.getCurrentUrl().endsWith("aet/private/main.xhtml")){
            System.out.println("SUCCESS");
            Assert.assertTrue(true);
        }else{
            System.out.println("FAILURE");
            Assert.assertTrue(false);
        }
    }
}


//        final Wait<WebDriver> wait = new WebDriverWait(driver, 8, 1000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".worker-avatar-clip")));