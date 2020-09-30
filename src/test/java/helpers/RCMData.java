package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RCMData {
    public String number;
    public String RWTrack;
    public String date;
    public String comment;

    public RCMData(String number, String RWTrack, String date, String comment){
        this.number = number;
        this.RWTrack = RWTrack;
        this.date = date;
        this.comment = comment;
    }

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

    public String newValidDate() {

        Date today = new Date();
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yy HH:mm");
        return formatterForDate.format(today);

    }
}
