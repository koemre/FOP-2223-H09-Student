package h09.sequence;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Map;

@TestForSubmission
public final class BasicFactorySequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            BasicFactorySequence.class, Sequence.class, Map.of("T", Object.class));
    }
}
