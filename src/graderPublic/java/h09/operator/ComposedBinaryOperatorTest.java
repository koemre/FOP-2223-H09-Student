package h09.operator;

import com.google.common.collect.Sets;
import h09.FieldExtensions;
import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class ComposedBinaryOperatorTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            ComposedBinaryOperator.class, BinaryOperator.class, Map.of("T", Object.class));
    }

    @Test
    void testFields() {
        final TypeVariable<Class<ComposedBinaryOperator>> genericT = ComposedBinaryOperator.class.getTypeParameters()[0];
        final Field[] fields = ComposedBinaryOperator.class.getDeclaredFields();
        Assertions.assertEquals(3, fields.length, "ComposedBinaryOperator should have three fields");
        FieldExtensions.assertPrivateFinal("ComposedBinaryOperator", fields);
        for (final Field field : fields) {
            Assertions.assertEquals(BinaryOperator.class, field.getType(),
                "ComposedBinaryOperator." + field.getName() + " should be of type BinaryOperator<T>");
            if (field.getGenericType() instanceof final ParameterizedType type) {
                Assertions.assertEquals(genericT, type.getActualTypeArguments()[0],
                    "Field ComposedBinaryOperator." + field.getName() + " should be of type BinaryOperator<T>");
            } else {
                Assertions.fail("Field ComposedBinaryOperator." + field.getName() + " is not a parameterized type");
            }
        }
    }

    @Test
    void testConstructor() {
        final Constructor<ComposedBinaryOperator> constructor = Assertions.assertDoesNotThrow(() ->
                ComposedBinaryOperator.class.getDeclaredConstructor(
                    BinaryOperator.class, BinaryOperator.class, BinaryOperator.class),
            "ComposedBinaryOperator should have a constructor with signature"
                + "ComposedBinaryOperator(BinaryOperator<T>, BinaryOperator<T>, BinaryOperator<T>)"
        );
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "Constructor ComposedBinaryOperator(BinaryOperator<T>, BinaryOperator<T>, BinaryOperator<T>) should be public");
        final Type[] genericParams = constructor.getGenericParameterTypes();
        final TypeVariable<Class<ComposedBinaryOperator>> T = ComposedBinaryOperator.class.getTypeParameters()[0];
        for (int i = 0; i < 3; i++) {
            if (genericParams[i] instanceof final ParameterizedType type) {
                Assertions.assertEquals(T, type.getActualTypeArguments()[0],
                    "Parameter " + i + " of ComposedDoubleBinaryOperator should be of type BinaryOperator<T>");
            } else {
                Assertions.fail("Parameter " + i + " of ComposedDoubleBinaryOperator is not a parameterized type");
            }
        }
    }

    @Test
    void testApplySignature() {
        GenericBinaryOperatorExtensions.testApplySignature(ComposedBinaryOperator.class);
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testApply() {
        final BinaryOperator<String> concat = (a, b) -> a + b;
        final BinaryOperator<String> intersect = (a, b) -> {
            final Set<Character> foo = Sets.intersection(
                a.chars().mapToObj(c -> (char) c).collect(HashSet::new, HashSet::add, HashSet::addAll),
                b.chars().mapToObj(c -> (char) c).collect(HashSet::new, HashSet::add, HashSet::addAll)
            );
            return foo.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        };
        final Constructor<ComposedBinaryOperator> constructor = Assertions.assertDoesNotThrow(() ->
                ComposedBinaryOperator.class.getDeclaredConstructor(
                    BinaryOperator.class, BinaryOperator.class, BinaryOperator.class),
            "ComposedBinaryOperator does not have a correct constructor");
        final ComposedBinaryOperator operator =
            InvokeAssertions.assertDoesNotThrow(() -> constructor.newInstance(concat, intersect, concat),
                "Failed to invoke ComposedBinaryOperator constructor");
        Assertions.assertEquals("abbcb", BinaryOperatorInvoker.invokeApply(operator, "ab", "bc"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"ab\", \"bc\") should return \"abbcb\"");
        Assertions.assertEquals("abcd", BinaryOperatorInvoker.invokeApply(operator,"ab", "cd"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"ab\", \"cd\") should return \"abcd\"");
        Assertions.assertEquals("ababab", BinaryOperatorInvoker.invokeApply(operator,"ab", "ab"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"ab\", \"ab\") should return \"ababab\"");
        Assertions.assertEquals("helloworldlo", BinaryOperatorInvoker.invokeApply(operator,"hello", "world"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"hello\", \"world\") should return \"helloworldlo\"");
    }
}
