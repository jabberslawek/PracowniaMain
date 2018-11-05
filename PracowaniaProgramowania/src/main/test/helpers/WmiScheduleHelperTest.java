import helpers.WmiScheduleHelper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Date;

class WmiScheduleHelperTest {

    private static WmiScheduleHelper wmi;

    @BeforeAll
    static void init() {
        wmi = new WmiScheduleHelper();
    }

    //Correct
    @Test
    void calculateFridayAfterActivities() {
        Date date = new Date(118, 10, 9, 21, 40, 10);
        Assert.assertEquals(wmi.calculateMinutes(date, 3375), 3514);
    }

    @Test
    void calculateSaturday() {
        Date date = new Date(118, 10, 10, 15, 26, 0);
        Assert.assertEquals(wmi.calculateMinutes(date, 1935), 2448);
    }

    @Test
    void calculateSunday() {
        Date date = new Date(118, 10, 11, 7, 12, 30);
        Assert.assertEquals(wmi.calculateMinutes(date, 495), 1502);
    }

    @Test
    void calulateBreak() {
        Date date = new Date(118, 10, 11, 9, 59, 30);
        Date start = new Date(118, 10, 11, 9, 45, 1);
        Date end = new Date(118, 10, 11, 10, 0, 0);
        int result = (end.getHours() * 60 + end.getMinutes()) - (date.getHours() * 60 + date.getMinutes()) - 1;
        Assert.assertEquals(result, 0);
    }

    //Failed
    @Test
    void calculateFridaAfterActivitiesFailed() {
        Date date = new Date(118, 10, 9, 21, 40, 10);
        Assert.assertNotEquals(wmi.calculateMinutes(date, 3375), 4599);
    }

    @Test
    void calculateSaturdayFailed() {
        Date date = new Date(118, 10, 10, 15, 26, 0);
        Assert.assertNotEquals(wmi.calculateMinutes(date, 1935), 5679);
    }

    @Test
    void calculateSundayFailed() {
        Date date = new Date(118, 10, 11, 7, 12, 30);
        Assert.assertNotEquals(wmi.calculateMinutes(date, 495), 460);
    }

    @Test
    void calulateBreakFailed() {
        Date date = new Date(118, 10, 11, 9, 47, 30);
        Date start = new Date(118, 10, 11, 9, 45, 1);
        Date end = new Date(118, 10, 11, 10, 0, 0);
        int result = (end.getHours() * 60 + end.getMinutes()) - (date.getHours() * 60 + date.getMinutes()) - 1;
        Assert.assertNotEquals(result, 0);
    }

    @Test
    void calulateActivityFailed() {
        Date date = new Date(118, 10, 11, 17, 37, 30);
        Date start = new Date(118, 10, 11, 17, 15, 1);
        Date end = new Date(118, 10, 11, 18, 45, 0);
        int result = (end.getHours() * 60 + end.getMinutes()) - (date.getHours() * 60 + date.getMinutes()) - 1;
        Assert.assertNotEquals(result, 128);
    }

}