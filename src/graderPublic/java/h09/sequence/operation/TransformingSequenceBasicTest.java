package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class TransformingSequenceBasicTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<TransformingSequence>>[] typeParameters = TransformingSequence.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T", "R"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "TransformingSequence should have two type parameters T and R");
        final TypeVariable<Class<TransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<TransformingSequence>> genericR = typeParameters[1];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "TransformingSequence's generic type T should not have additional bounds");
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericR.getBounds(),
            "TransformingSequence's generic type R should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(TransformingSequence.class, Sequence.class, genericR);
    }

    @Test
    void testIteratorBasic() {
        final Constructor<TransformingSequence> constructor = Assertions.assertDoesNotThrow(() ->
                TransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class),
            "TransformingSequence does not have a correct constructor");
        final Sequence<Integer> og = Sequence.of(1, 2, 3, 4, 5);
        final TransformingSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(og, (Function<Integer, Double>) x -> x * 10.5),
            "TransformingSequence constructor should not throw an exception");
        final Iterator it = sequence.iterator();
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(it.hasNext(), "TransformingSequence should have 5 elements");
            Assertions.assertEquals((i + 1) * 10.5, it.next(), "TransformingSequence should have elements 10, 20, 30, 40, 50");
        }
    }
}
