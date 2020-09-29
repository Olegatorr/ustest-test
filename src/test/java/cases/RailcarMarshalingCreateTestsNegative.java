/*
package robotest.test.cases;

import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase;
import helpers.RailcarMarshalingData;
import RailcarMarshalingFillPage;
import RailcarMarshalingViewPage;

public class RailcarMarshalingCreateTestsNegative extends TestBase {

    RailcarMarshalingFillPage railcarMarshalingFillPage;
    RailcarMarshalingData data;

    @Story("negative create Railcar Marshaling 1")
    @Test(priority = 1)
    public void CreateRailcarMarshalingNegativeExample1() {

        goToRailcarMarshaling();
        clickNew();

        // create new page object
        railcarMarshalingFillPage = new RailcarMarshalingFillPage(driver, wait);

        // generate valid field data for all fields
        data = RailcarMarshalingData.createValidData();
        // break some data
        data.date = "";
        // create RWM, expecting a failure
        railcarMarshalingFillPage.makeRailcarMarshalingFail(data);
        // check if specific expected error is present
        Assert.assertEquals(
                railcarMarshalingFillPage.getErrorMessage(),
                "Marshaling date: Validation Error: Value is required.",
                "Comparing error message");

    }

    @Story("negative create Railcar Marshaling 2")
    @Test (priority = 2)
    public void CreateRailcarMarshalingNegativeExample2() {

        data = RailcarMarshalingData.createValidData();
        data.number = "";
        railcarMarshalingFillPage.makeRailcarMarshalingFail(data);
        Assert.assertEquals(
                railcarMarshalingFillPage.getErrorMessage(),
                "Number: Validation Error: Value is required.",
                "Comparing error message");

    }
}
*/