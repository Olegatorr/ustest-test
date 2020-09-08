package step.by.step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;




public class RwTrainVizitTests extends TestBase{

    @Test
    public void testCreateRwTrainVizit() {

        goToMainPage();
        goToRailcarMarshaling();
        clickNew();

        RWTrainVizitFillName("test test");
        RWTrainVizitFillRWTrack("RAILWAY");
        RWTrainVizitFillDate("08/09/20 00:00");
        RWTrainVizitFillComment("comment");

        RWTrainVizitSave();

        if(driver.findElement(By.className("ui-messages-error")).isDisplayed()){
            Assert.fail("");
        }
;
        // By.cssSelector(".new"))

    }
}



