package h09.sequence.collect;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class BinaryOpFoldCollectorBasicTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<BinaryOpFoldCollector>>[] typeParameters = BinaryOpFoldCollector.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "BinaryOpFoldCollector should have a type parameter T");
        final TypeVariable<Class<BinaryOpFoldCollector>> genericT = typeParameters[0];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "BinaryOpFoldCollector's generic type T should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(BinaryOpFoldCollector.class, SequenceCollector.class, genericT, genericT);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testCollectBasic() {
        final Constructor<BinaryOpFoldCollector> constructor = Assertions.assertDoesNotThrow(() ->
                BinaryOpFoldCollector.class.getDeclaredConstructor(Object.class, BinaryOperator.class),
            "BinaryOpFoldCollector does not have a correct constructor");
        final SequenceCollector<Integer, Integer> collector = InvokeAssertions.assertDoesNotThrow(() ->
                (SequenceCollector<Integer, Integer>) constructor.newInstance(1,
                    (BinaryOperator<Integer>) (a, b) -> a * b),
            "BinaryOpFoldCollector does not have a correct constructor");
        final int product = Sequence.of(1, 2, 3, 4, 5).collect(collector);
        Assertions.assertEquals(120, product);
    }
}
