package step.by.step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RwTrainVizitTests extends TestBase{

    String id = "";

    @Test
    public void testCreateRwTrainVizit() {

        goToMainPage();
        goToRailcarMarshaling();
        clickNew();

        // get a unique name
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

        id = getURLID(driver.getCurrentUrl());

        // get all ui-g-4 blocks of 2 DIVs: label and data
        List<WebElement> fields = driver.findElements(By.className("ui-g-4"));
        // assert all fields
        Assert.assertEquals("Comparing number" , RWTrainVizitName , checkFieldData(fields, "Number"));
        Assert.assertEquals("Comparing track"  , RWTrainVizitRWTrack , checkFieldData(fields, "RW track"));
        Assert.assertEquals("Comparing date"   , RWTrainVizitDate , checkFieldData(fields, "Marshaling date"));
        Assert.assertEquals("Comparing comment", RWTrainVizitComment , checkFieldData(fields, "Comments"));
        Assert.assertTrue("Comparing #", driver.findElement(By.id("object_card_header")).getText().contains(id));

        // TODO: ask if it is reasonable to go *foreach link* to check this?
        // goToRailcarMarshalingShort();
        // Assert.assertTrue(driver.findElement(By.linkText(id)).isDisplayed());

    }

    @Test
    public void testEditRwTrainVizit(){

        //assuming we are on the same RWM page
        clickEdit();


    }
}



