
package cases;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import base.TestBase;
import helpers.RCMData;
import pages.RCMFillPage;
import pages.RCMViewPage;

public class RCMCreateTestsPositive extends TestBase {

    RCMData data;

    @Story("positive create Railcar Marshaling")
    @Test (priority = 1)
    public void CreateRailcarMarshalingPositive() {

        goToRCM();
        clickNew();

        RCMFillPage fillPage = new RCMFillPage();
        data = RCMData.createValidData();
        RCMViewPage viewPage = fillPage.makeRCMSuccess(data);
        Assert.assertTrue(viewPage.isOpen());
        viewPage.validateData(data);

    }


    @Story("positive edit Railcar Marshaling")
    @Test (priority = 2)
    public void EditRailcarMarshalingPositive(){

        // assuming we are on the same RCM page
        // TODO: *LATER* figure out a way to save the ID (then explicitly go to created RCM)

        clickEdit();

        RCMFillPage RCMFillPage = new RCMFillPage();

        data.number = data.number + " edited";
        data.RWTrack = "RT01";
        data.date = data.newValidDate();
        data.comment = data.comment + " edited";

        RCMViewPage viewPage = RCMFillPage.makeRCMSuccess(data);
        Assert.assertTrue(viewPage.isOpen());
        viewPage.validateData(data);

    }
}

