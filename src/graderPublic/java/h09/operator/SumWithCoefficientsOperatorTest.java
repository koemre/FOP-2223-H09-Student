package h09.operator;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import h09.basic.BasicBinaryOperations;
import h09.basic.StringBasicBinaryOperations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public class SumWithCoefficientsOperatorTest {

    @Test
    void testSignature() {
        // linked hash map to preserve insertion order
        final Map<String, Type> genericParams = new LinkedHashMap<>();
        genericParams.put("X", Object.class);
        genericParams.put("Y", Object.class);
        SignatureTestExtensions.testGenericDeclaration(SumWithCoefficientsOperator.class, genericParams);
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericX = SumWithCoefficientsOperator.class.getTypeParameters()[0];
        SignatureTestExtensions.testGenericSuperInterface(SumWithCoefficientsOperator.class, BinaryOperator.class, genericX);
    }

    @Test
    void testFields() {
        final TypeVariable<Class<SumWithCoefficientsOperator>>[] typeParameters = SumWithCoefficientsOperator.class.getTypeParameters();
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericX = typeParameters[0];
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericY = typeParameters[1];
        final Field[] fields = SumWithCoefficientsOperator.class.getDeclaredFields();
        Assertions.assertEquals(2, Stream.of(fields).filter(field -> field.getGenericType().equals(genericY)).count(),
            "SumWithCoefficientsOperator should have exactly two fields of generic type Y");
        final Field opField = Stream.of(fields).filter(field -> field.getType().equals(BasicBinaryOperations.class)).findAny()
            .orElseThrow(() -> new AssertionFailedError("SumWithCoefficientsOperator should have a field who's raw type is BasicBinaryOperations"));
        if (opField.getGenericType() instanceof ParameterizedType type) {
            final Type[] actualTypeArguments = type.getActualTypeArguments();
            Assertions.assertArrayEquals(new Type[]{genericX, genericY}, actualTypeArguments,
                "SumWithCoefficientsOperator should have a field who's raw type is BasicBinaryOperations<X, Y>");
        } else {
            Assertions.fail("Field SumWithCoefficientsOperator." + opField.getName() + " should be of a parameterized type");
        }
    }

    @Test
    void testConstructor() {
        final Constructor<SumWithCoefficientsOperator> constructor = Assertions.assertDoesNotThrow(() ->
                SumWithCoefficientsOperator.class.getDeclaredConstructor(
                    BasicBinaryOperations.class, Object.class, Object.class),
            "SumWithCoefficientsOperator does not have a correct constructor");
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "Constructor for SumWithCoefficientsOperator should be public");
        final Type[] genericParams = constructor.getGenericParameterTypes();
        final TypeVariable<Class<SumWithCoefficientsOperator>>[] typeParameters = SumWithCoefficientsOperator.class.getTypeParameters();
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericX = typeParameters[0];
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericY = typeParameters[1];
        if (genericParams[0] instanceof final ParameterizedType type) {
            Assertions.assertArrayEquals(new Type[]{genericX, genericY}, type.getActualTypeArguments(),
                "The first parameter of the constructor for SumWithCoefficientsOperator is not parameterized correctly");
        } else {
            Assertions.fail("The first parameter of constructor for SumWithCoefficientsOperator should be parameterized");
        }
        Assertions.assertEquals(genericY, genericParams[1],
            "Constructor for SumWithCoefficientsOperator should have a second parameter of type Y");
        Assertions.assertEquals(genericY, genericParams[2],
            "Constructor for SumWithCoefficientsOperator should have a third parameter of type Y");
    }

    @Test
    void testApplySignature() {
        GenericBinaryOperatorExtensions.testApplySignature(SumWithCoefficientsOperator.class);
    }

    @Test
    void testApply() {
        final StringBasicBinaryOperations op = new StringBasicBinaryOperations();
        final Constructor<SumWithCoefficientsOperator> sumCtor = Assertions.assertDoesNotThrow(() ->
                SumWithCoefficientsOperator.class.getDeclaredConstructor(
                    BasicBinaryOperations.class, Object.class, Object.class),
            "SumWithCoefficientsOperator does not have a correct constructor");
        final SumWithCoefficientsOperator sum = InvokeAssertions.assertDoesNotThrow(() ->
                sumCtor.newInstance(op, 3, 2),
            "Failed to invoke constructor for SumWithCoefficientsOperator");
        Assertions.assertEquals("abababcdcd", BinaryOperatorInvoker.invokeApply(sum,"ab", "cd"),
            "new SumWithCoefficientsOperator(new StringBasicBinaryOperations(), 3, 2).apply(\"ab\", \"cd\") should return \"abababcdcd\"");
        Assertions.assertEquals("aaacdaacdaa", BinaryOperatorInvoker.invokeApply(sum,"a", "cdaa"),
            "new SumWithCoefficientsOperator(new StringBasicBinaryOperations(), 3, 2).apply(\"a\", \"cdaa\") should return \"aaacdaacdaa\"");
    }
}
