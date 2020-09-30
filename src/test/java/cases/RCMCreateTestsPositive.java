
package cases;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import base.TestBase;
import helpers.RCMData;
import pages.RCMFillPage;
import pages.RCMViewPage;

/**
 * Test class for RCM body positive tests
 *
 * Example loginNegativeWrongLogin():
 *
 * goToRCM();
 * open RCM page
 *
 * clickNew();
 * click New RCM on the RCM view page
 *
 * RCMFillPage fillPage = new RCMFillPage();
 * create new fillPage page object
 *
 * data = RCMData.createValidData();
 * create new RCMData object (contains String for all the fields)
 *
 * RCMViewPage viewPage = fillPage.makeRCMSuccess(data);
 * call makeRCMSuccess method of fillPage page object
 * fill the fields using RCMData and submit the RCM
 * since RCM creation is expected to succeed, viewPage object is returned
 *
 * Assert.assertTrue(viewPage.isOpen());
 * check if the viewPage page is open
 *
 * viewPage.validateData(data);
 * validate data on the page
 * compares data on the page with data we entered while creating RCM
 */
public class RCMCreateTestsPositive extends TestBase {


    /**
     * RCM data object
     * Contains String data for all the fields
     */
    RCMData data;

    /**
     *  Positive test of RCM body creation
     *  RCM is expected to be successfully created
     */
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

    /**
     *  Positive test of RCM body edit
     *  RCM is expected to be successfully edited
     */
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

