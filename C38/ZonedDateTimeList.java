package C38;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ZonedDateTimeList implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<ZonedDateTime> dateTimes;

    public ZonedDateTimeList() {
        this.dateTimes = new ArrayList<>();
    }

    public ArrayList<ZonedDateTime> getDateTimes() {
        return dateTimes;
    }

    public void addDateTime(ZonedDateTime dateTime) {
        dateTimes.add(dateTime);
    }
}
