package helpers;

import models.WmiSchedule;

import java.util.ArrayList;
import java.util.Date;

public class WmiScheduleHelper {

    private boolean type;
    private ArrayList<WmiSchedule> wmiPlan = new ArrayList<WmiSchedule>() {{
        add(new WmiSchedule("08:15:00", "9:45:00", false));
        add(new WmiSchedule("09:45:01", "10:00:00", true));
        add(new WmiSchedule("10:00:00", "11:30:00", false));
        add(new WmiSchedule("11:30:01", "11:45:00", true));
        add(new WmiSchedule("11:45:01", "13:15:00", false));
        add(new WmiSchedule("11:15:01", "13:45:00", true));
        add(new WmiSchedule("13:45:01", "15:15:00", false));
        add(new WmiSchedule("15:15:01", "15:30:00", true));
        add(new WmiSchedule("15:30:01", "17:00:00", false));
        add(new WmiSchedule("17:00:01", "17:15:00", true));
        add(new WmiSchedule("17:15:01", "18:45:00", false));
    }};

    public int checkActivity(Date dateNow) {
        Date start = new Date(), end = new Date();
        for (WmiSchedule activity : wmiPlan) {
            start = setUpDate(start, activity.getStartActivity());
            end = setUpDate(end, activity.getEndActivity());
            if (dateNow.getDay() >= 1 && dateNow.getDay() <= 5) {
                if (dateNow.after(start) && dateNow.before(end)) {
                    this.type = activity.isBreak();
                    return (end.getHours() * 60 + end.getMinutes()) - (dateNow.getHours() * 60 + dateNow.getMinutes()) - 1;
                }
            }
        }

        this.type = false;
        if (dateNow.getDay() == 5)
            return calculateMinutes(dateNow, 3375);
        if (dateNow.getDay() == 6)
            return calculateMinutes(dateNow, 1935);
        else
            return calculateMinutes(dateNow, 495);
    }

    private int getHours(String date) {
        return Integer.parseInt(date.substring(0, date.indexOf(":")));
    }

    private int getMinutes(String date) {
        return Integer.parseInt(date.substring(date.indexOf(":") + 1, date.lastIndexOf(":")));
    }

    private int getSeconds(String date) {
        return Integer.parseInt(date.substring(date.lastIndexOf(":") + 1));
    }

    private Date setUpDate(Date date, String activityHours) {
        date.setHours(getHours(activityHours));
        date.setMinutes(getMinutes(activityHours));
        date.setSeconds(getSeconds(activityHours));
        return date;
    }

    public int calculateMinutes(Date date, int value) {
        return (1440 - (date.getHours() * 60 + date.getMinutes())) + value - 1;
    }

    public boolean isType() {
        return type;
    }
}
