package h09.sequence;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.util.Map;

@TestForSubmission
public final class ArraySequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            ArraySequence.class, Sequence.class, Map.of("T", Object.class));
    }

    @Test
    @SuppressWarnings("rawtypes")
    void testIteratorBasic() {
        final Constructor<ArraySequence> constructor = Assertions.assertDoesNotThrow(() ->
            ArraySequence.class.getDeclaredConstructor(Object[].class),
            "ArraySequence does not have a correct constructor");
        final ArraySequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
            constructor.newInstance(new Object[]{new Integer[]{1, 2, 3}}),
            "Failed to invoke ArraySequence constructor");
        final var iterator = sequence.iterator();
        Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
        Assertions.assertEquals(1, iterator.next(), "Iterator should return the first element");
        Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
        Assertions.assertEquals(2, iterator.next(), "Iterator should return the second element");
        Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
        Assertions.assertEquals(3, iterator.next(), "Iterator should return the third element");
        Assertions.assertFalse(iterator.hasNext(), "Iterator should not have a next element");
    }
}
