package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RCMData class for RCMFillPage
 * Contains field data for each interactive field
 * Additionally contains method createValidData which creates valid data for all the fields
 */

public class RCMData {
    public String number;
    public String RWTrack;
    public String date;
    public String comment;


    /**
     * Constructor
     *
     * @param number RCM Number (Name)
     * @param RWTrack RCM RW Track
     * @param date RCM creation date
     * @param comment RCM comment
     */
    public RCMData(String number, String RWTrack, String date, String comment){
        this.number = number;
        this.RWTrack = RWTrack;
        this.date = date;
        this.comment = comment;
    }


    /**
     *  createValidData generates valid data for all the fields on the RCMFillPage
     *
     * @return valid RCMData object, ready to be entered
     */
    public static RCMData createValidData(){
        Date today = new Date();
        SimpleDateFormat formatterForName = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yy HH:mm");

        return new RCMData(
                formatterForName.format(today),
                "RAILWAY",
                formatterForDate.format(today),
                "comment"
        );
    }

    /**
     *  newValidDate generates valid date (and only date) and formats it as it is expected for RCM Creation Date field
     *  on the RCMFill page
     *
     * @return valid String with formatted Date, ready to be entered
     */
    public String newValidDate() {

        Date today = new Date();
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yy HH:mm");
        return formatterForDate.format(today);

    }
}
