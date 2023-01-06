package h09.operator;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public class MaxOfTwoOperatorTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<MaxOfTwoOperator>>[] typeParameters = MaxOfTwoOperator.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "MaxOfTwoOperator should have a single type parameter T");
        final TypeVariable<Class<MaxOfTwoOperator>> typeParameter = typeParameters[0];
        final Type[] bounds = typeParameter.getBounds();
        Assertions.assertEquals(1, bounds.length,
            "MaxOfTwoOperator should not have additional bounds on type parameter T");
        if (bounds[0] instanceof final ParameterizedType type) {
            Assertions.assertEquals(Comparable.class, type.getRawType(),
                "MaxOfTwoOperator should have a type parameter T with bound who's raw type is Comparable");
            if (type.getActualTypeArguments()[0] instanceof final WildcardType wildcard) {
                Assertions.assertEquals(1, wildcard.getUpperBounds().length,
                    "MaxOfTwoOperator's generic type T's upper bound Comparable should not have additional upper bounds");
                Assertions.assertEquals(Object.class, wildcard.getUpperBounds()[0],
                    "MaxOfTwoOperator's generic type T's upper bound Comparable should not have an explicit upper bound");
                Assertions.assertEquals(1, wildcard.getLowerBounds().length,
                    "MaxOfTwoOperator's generic type T's upper bound Comparable should have exactly one lower bound");
                Assertions.assertEquals(typeParameter, wildcard.getLowerBounds()[0],
                    "MaxOfTwoOperator's generic type T's upper bound Comparable is not parameterized correctly");
            } else if (type.getActualTypeArguments()[0] instanceof final TypeVariable<?> variable) {
                Assertions.fail("MaxOfTwoOperator's generic type T's upper bound Comparable is not parameterized correctly for"
                    + " maximum generic compatibility");
            } else {
                Assertions.fail("MaxOfTwoOperator should have a type parameter T with bound who's raw type is Comparable<T>");
            }
        } else {
            Assertions.assertEquals(Comparable.class, bounds[0],
                "MaxOfTwoOperator should have a type parameter T with bound who's raw type is Comparable");
            Assertions.fail("MaxOfTwoOperator's generic type T's upper bound is not parameterized");
        }
        SignatureTestExtensions.testGenericSuperInterface(MaxOfTwoOperator.class, BinaryOperator.class, typeParameter);
    }

    @Test
    void testApplySignature() {
        GenericBinaryOperatorExtensions.testApplySignature(ComposedBinaryOperator.class);
    }

    @Test
    void testApplyIntegers() {
        final MaxOfTwoOperator operator = new MaxOfTwoOperator();
        Assertions.assertEquals(7, BinaryOperatorInvoker.invokeApply(operator, 7, 4));
        Assertions.assertEquals(5, BinaryOperatorInvoker.invokeApply(operator, -3, 5));
        Assertions.assertEquals(-2, BinaryOperatorInvoker.invokeApply(operator, -5, -2));
    }

    @Test
    void testApplyStrings() {
        final MaxOfTwoOperator operator = new MaxOfTwoOperator();
        Assertions.assertEquals("zebra", BinaryOperatorInvoker.invokeApply(operator, "zebra", "apple"));
        Assertions.assertEquals("zebra", BinaryOperatorInvoker.invokeApply(operator, "apple", "zebra"));
        Assertions.assertEquals("b12d", BinaryOperatorInvoker.invokeApply(operator, "a231", "b12d"));
        Assertions.assertEquals("b12d", BinaryOperatorInvoker.invokeApply(operator, "", "b12d"));
    }
}
