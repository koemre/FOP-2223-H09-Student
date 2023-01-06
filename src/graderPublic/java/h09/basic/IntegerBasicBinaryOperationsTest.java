package h09.basic;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class IntegerBasicBinaryOperationsTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureWithNonGenericBase(
            IntegerBasicBinaryOperations.class, BasicBinaryOperations.class, Integer.class, Integer.class);    }

    @Test
    void testAdd() {
        final IntegerBasicBinaryOperations operations = new IntegerBasicBinaryOperations();
        for (int i = -20; i < 20; i++) {
            for (int j = -20; j < 20; j++) {
                Assertions.assertEquals(i + j, BasicBinaryOperationInvoker.invokeAdd(operations, i, j),
                    i + " + " + j + " should be " + (i + j));
            }
        }
    }

    @Test
    void testMul() {
        final IntegerBasicBinaryOperations operations = new IntegerBasicBinaryOperations();
        for (int i = -20; i < 20; i++) {
            for (int j = -20; j < 20; j++) {
                Assertions.assertEquals(i * j, BasicBinaryOperationInvoker.invokeMul(operations, i, j),
                    i + " * " + j + " should be " + (i * j));
            }
        }
    }
}
