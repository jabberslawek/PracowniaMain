
import helpers.CitizenHelper;
import org.junit.Assert;
import org.junit.jupiter.api.*;

class CitizenHelperTest {

    @Test
    void cutPeselCorrect()  {
        String pesel = CitizenHelper.cutPesel("M L 97010402339");
        Assert.assertEquals(pesel, "97010402339");
    }

    @Test
    void cutPeselFailed() {
        String pesel = CitizenHelper.cutPesel("M L 97010402338");
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