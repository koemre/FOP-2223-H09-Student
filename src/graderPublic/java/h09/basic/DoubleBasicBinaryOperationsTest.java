package h09.basic;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class DoubleBasicBinaryOperationsTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureWithNonGenericBase(
            DoubleBasicBinaryOperations.class, BasicBinaryOperations.class, Double.class, Double.class);
    }

    @Test
    void testAdd() {
        final DoubleBasicBinaryOperations operations = new DoubleBasicBinaryOperations();
        for (double i = -10; i < 10; i += 0.5) {
            for (double j = -10; j < 10; j += 0.25) {
                Assertions.assertEquals(i + j, BasicBinaryOperationInvoker.invokeAdd(operations, i, j),
                    i + " + " + j + " should be " + (i + j));
            }
        }
    }

    @Test
    void testMul() {
        final DoubleBasicBinaryOperations operations = new DoubleBasicBinaryOperations();
        for (double i = -10; i < 10; i += 0.5) {
            for (double j = -10; j < 10; j += 0.25) {
                Assertions.assertEquals(i * j, BasicBinaryOperationInvoker.invokeMul(operations, i, j),
                    i + " * " + j + " should be " + (i * j));
            }
        }
    }
}
