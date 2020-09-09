package step.by.step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;


public class RwTrainVizitTests extends TestBase{

    @Test
    public void testCreateRwTrainVizit() {

        goToMainPage();
        goToRailcarMarshaling();
        clickNew();

        // get a unickue name
        Date date = new Date();
        SimpleDateFormat formatterForName = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yy HH:mm");

        String RWTrainVizitName = formatterForName.format(date);
        String RWTrainVizitRWTrack = "RAILWAY";
        String RWTrainVizitDate = formatterForDate.format(date);
        String RWTrainVizitComment = "comment";

        RWTrainVizitFillName(RWTrainVizitName);
        RWTrainVizitFillRWTrack(RWTrainVizitRWTrack);
        RWTrainVizitFillDate(RWTrainVizitDate);
        RWTrainVizitFillComment(RWTrainVizitComment);

        RWTrainVizitSave();

        String id = getURLID(driver.getCurrentUrl());

        //if(driver.findElement(By.className("ui-messages-error")).isDisplayed()){
        //    Assert.fail("There were errors while saving the marshaling"); // TODO: parse errors <li>'s to here
        //}

        // get all ui-g-4 blocks of 2 DIVs: label and data
        List<WebElement> fields = driver.findElements(By.className("ui-g-4"));

        Assert.assertEquals("Comparing label and data", RWTrainVizitName , checkFieldData(fields, "Number"));
        Assert.assertEquals("Comparing label and data", RWTrainVizitRWTrack , checkFieldData(fields, "RW track"));
        Assert.assertEquals("Comparing label and data", RWTrainVizitDate , checkFieldData(fields, "Marshaling date"));
        Assert.assertEquals("Comparing label and data", RWTrainVizitComment , checkFieldData(fields, "Comments"));

        //goToRailcarMarshalingShort();
        //Assert.assertTrue(driver.findElement(By.linkText(id)).isDisplayed());

    }
}



