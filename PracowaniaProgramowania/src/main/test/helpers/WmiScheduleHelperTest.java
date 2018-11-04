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

    @Test
    void checkActivityNotWeekend() {
        Date date = new Date(118, 10, 5, 8, 16, 10);
        Assert.assertEquals(wmi.checkActivity(date), 59);
    }
}