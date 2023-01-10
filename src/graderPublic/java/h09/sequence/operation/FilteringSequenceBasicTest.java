package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@TestForSubmission
public final class FilteringSequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            FilteringSequence.class, Sequence.class, Map.of("T", Object.class));
    }

    @Test
    @SuppressWarnings({"rawtypes"})
    void testIteratorBasic() {
        final Constructor<FilteringSequence> constructor = Assertions.assertDoesNotThrow(() ->
            FilteringSequence.class.getDeclaredConstructor(Sequence.class, Predicate.class),
            "FilteringSequence does not have a correct constructor");
        final Sequence<String> og = Sequence.of(List.of("aa", "bbb", "cc", "d", "eee", "ffff", "g", "hh", "ii", "jjj"));
        final FilteringSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
            constructor.newInstance(og, (Predicate<String>) s -> s.length() > 2),
            "Failed to invoke FilteringSequence constructor");
        final List result = List.of("bbb", "eee", "ffff", "jjj");
        final Iterator it = sequence.iterator();
        for (final Object o : result) {
            Assertions.assertTrue(it.hasNext(), "Iterator should have a next element");
            Assertions.assertEquals(o, it.next(), "Iterator should return the correct element");
        }
    }
}
