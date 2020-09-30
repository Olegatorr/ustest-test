package forms;

import base.TableDropDown;
import base.ListBaseElement;

public class RWTrackTable extends TableDropDown {
    static String optTemp = "//*[@id=\"RwTrainVizitEditForm:way:ac_panel\"]/table/tbody/tr[%s]";
    static ListBaseElement options = new ListBaseElement("//*[@id=\"RwTrainVizitEditForm:way:ac_panel\"]/table/tbody/tr");

    public RWTrackTable(String locator) {
        super(options,optTemp);
    }
}
