package h09.sequence.collect;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import h09.basic.BasicBinaryOperations;
import h09.basic.IntegerBasicBinaryOperations;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class SummingCollectorBasicTest {
    @Test
    void testSignature() {
        final TypeVariable<Class<SummingCollector>>[] typeParameters = SummingCollector.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "SummingCollector should have a type parameter T");
        final TypeVariable<Class<SummingCollector>> genericT = typeParameters[0];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "SummingCollector's generic type T should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(SummingCollector.class, SequenceCollector.class, genericT, genericT);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testCollectBasic() {
        final Constructor<SummingCollector> constructor = Assertions.assertDoesNotThrow(() ->
                SummingCollector.class.getDeclaredConstructor(Object.class, BasicBinaryOperations.class),
            "SummingCollector does not have a correct constructor");
        final SequenceCollector<Integer, Integer> collector = InvokeAssertions.assertDoesNotThrow(() ->
                (SequenceCollector<Integer, Integer>) constructor.newInstance(0, new IntegerBasicBinaryOperations()),
            "SummingCollector does not have a correct constructor");
        final int sum = Sequence.of(1, 2, 3, 4, 5).collect(collector);
        Assertions.assertEquals(15, sum);
    }
}
