
import helpers.CitizenHelper;
import org.junit.Assert;
import org.junit.jupiter.api.*;

class CitizenHelperTest {

    @Test
    void cutPeselCorrect() {
        String correctData = "M L 44051401359";
        String pesel = CitizenHelper.cutPesel(correctData);
        Assert.assertEquals(pesel, "44051401359");
    }

    @Test
    void cutPeselRegexFailed() {
        String correctData = "M 44051401359";
        String pesel = CitizenHelper.cutPesel(correctData);
        Assert.assertNull(pesel);
    }

    @Test
    void cutPeselFailed() {
        String incorrectData = "M L 44051401358";
        String pesel = CitizenHelper.cutPesel(incorrectData);
        Assert.assertNull(pesel);
    }

    @Test
    void validatePeselCorrect() {
        Assert.assertTrue(CitizenHelper.validatePesel("44051401359"));
    }

    @Test
    void validatePeselFailed() {
        Assert.assertFalse(CitizenHelper.validatePesel("44051401351"));
    }

}
