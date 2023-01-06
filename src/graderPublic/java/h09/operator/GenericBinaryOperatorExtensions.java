package h09.operator;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.function.BinaryOperator;

public final class GenericBinaryOperatorExtensions {

    private GenericBinaryOperatorExtensions() {
    }

    static void testApplySignature(final Class<?> binaryOperatorClass) {
        final Method apply = Assertions.assertDoesNotThrow(() ->
                binaryOperatorClass.getDeclaredMethod("apply", Object.class, Object.class),
            binaryOperatorClass.getName() + " should have a method with signatur T apply(T, T)"
        );
        Assertions.assertTrue(Modifier.isPublic(apply.getModifiers()),
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should be public");
        Assertions.assertEquals(binaryOperatorClass.getTypeParameters()[0], apply.getGenericReturnType(),
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should return T");
        final TypeVariable<? extends Class<?>> T = binaryOperatorClass.getTypeParameters()[0];
        Assertions.assertEquals(T, apply.getGenericReturnType(),
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should return T");
        Assertions.assertEquals(T, apply.getGenericParameterTypes()[0],
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should have a first parameter of type T");
        Assertions.assertEquals(T, apply.getGenericParameterTypes()[1],
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should have a second parameter of type T");
    }
}
