package models;

import java.sql.Time;
import java.util.Date;

public class WmiSchedule {

    private String startActivity;
    private String endActivity;

    private boolean isBreak;

    public WmiSchedule(String startActivity, String endActivity, boolean isBreak) {
        this.startActivity = startActivity;
        this.endActivity = endActivity;
        this.isBreak = isBreak;
    }

    public String getStartActivity() {
        return startActivity;
    }

    public void setStartActivity(String startActivity) {
        this.startActivity = startActivity;
    }

    public String getEndActivity() {
        return endActivity;
    }

    public void setEndActivity(String endActivity) {
        this.endActivity = endActivity;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setBreak(boolean aBreak) {
        isBreak = aBreak;
    }
}
