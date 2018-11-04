
import helpers.CitizenHelper;
import org.junit.Assert;
import org.junit.jupiter.api.*;

class CitizenHelperTest {

    @Test
    void cutPeselCorrect()  {
        String incorrectData = "M L 44051401358";
        String pesel = CitizenHelper.cutPesel(incorrectData);
        Assert.assertEquals(pesel, "97010402339");
    }

    @Test
    void cutPeselFailed() {
        String correctData = "M L 44051401359";
        String pesel = CitizenHelper.cutPesel(correctData);
        Assert.assertNull(pesel);
    }

    @Test
    void validatePeselCorrect() {
        Assert.assertTrue(CitizenHelper.validatePesel("97010402339"));
    }

    @Test
    void validatePeselFailed() {
        Assert.assertFalse(CitizenHelper.validatePesel("97010402331"));
    }

    @Test
    void validatePeselException() {

    }



}