package h09.sequence;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class FibonacciSequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureWithNonGenericBase(FibonacciSequence.class, Sequence.class, Integer.class);
    }

    @Test
    void testIteratorBasic() {
        final FibonacciSequence sequence = new FibonacciSequence();
        final var iterator = sequence.iterator();
        int last = 0;
        int current = 1;
        for (int i = 0; i < 10; i++) {
            Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
            final Object next = iterator.next();
            Assertions.assertEquals(current, next, "Incorrect fibonacci number at index " + i);
            final int temp = current;
            current = last + current;
            last = temp;
        }
    }
}
