package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RailcarMarshalingData {
    public String number;
    public String RWTrack;
    public String date;
    public String comment;

    public RailcarMarshalingData(String number, String RWTrack, String date, String comment){
        this.number = number;
        this.RWTrack = RWTrack;
        this.date = date;
        this.comment = comment;
    }

    public static RailcarMarshalingData createValidData(){
        Date today = new Date();
        SimpleDateFormat formatterForName = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yy HH:mm");

        return new RailcarMarshalingData(
                formatterForName.format(today),
                "RAILWAY",
                formatterForDate.format(today),
                "comment"
        );
    }

}
