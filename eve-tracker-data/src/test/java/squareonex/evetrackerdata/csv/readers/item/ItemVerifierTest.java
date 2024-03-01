package squareonex.evetrackerdata.csv.readers.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemVerifierTest {
    ItemVerifier verifier;

    @BeforeEach
    void setUp() {
        this.verifier = new ItemVerifier();
    }

    @Test
    void verifyBeanShouldDetectInvalidDTO() {
        boolean isValid = assertDoesNotThrow(() -> verifier.verifyBean(new ItemDTO(0L, "Tritanium", false)));
        assertFalse(isValid);
    }
}