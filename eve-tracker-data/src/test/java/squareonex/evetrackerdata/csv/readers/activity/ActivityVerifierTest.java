package squareonex.evetrackerdata.csv.readers.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityVerifierTest {
    ActivityVerifier activityVerifier;

    @BeforeEach
    void setUp() {
        this.activityVerifier = new ActivityVerifier();
    }

    @Test
    void verifyBeanShouldDetectInvalidDTO() {
        Boolean isValid = assertDoesNotThrow(() -> activityVerifier.verifyBean(new ActivityDTO(0, "None", true)));
        assertFalse(isValid);

        isValid = assertDoesNotThrow(() -> activityVerifier.verifyBean(new ActivityDTO(0, "Duplicating", false)));
        assertFalse(isValid);
    }
}