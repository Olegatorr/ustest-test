package step.by.step;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends TestBase{

    @Test
    public void test() {

        //login is in BeforeTest, no need to call (for now)
        login(new LoginData("ROBOTESTER", "ELECTROSTALIN"));
        goToMainPage();
        wait.until(WebDriver::getTitle)

        if(driver.getCurrentUrl().endsWith("aet/private/main.xhtml")){ // TODO: make it slow (now - too fast)
            System.out.println("SUCCESS");
        }else{
            System.out.println("FAILURE");
            Assert.fail();
        }
    }
}


//        final Wait<WebDriver> wait = new WebDriverWait(driver, 8, 1000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".worker-avatar-clip")));