package h09;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("DuplicatedCode")
public class SignatureTestExtensions {

    @SuppressWarnings("unchecked")
    private static final TypeVariable<Class<?>>[] EXPECTED_TYPE_PARAMETERS =
        (TypeVariable<Class<?>>[]) new TypeVariable<?>[0];

    /**
     * Tests whether the given class has the correct signature.
     */
    public static void testSignatureWithNonGenericBase(final Class<?> targetClass,
                                                       final Class<?> targetSuperInterface,
                                                       final Class<?>... paramTypes) {
        Assertions.assertArrayEquals(EXPECTED_TYPE_PARAMETERS, targetClass.getTypeParameters(),
            targetClass + " should not have type parameters");
        testGenericSuperInterface(targetClass, targetSuperInterface, paramTypes);
    }

    public static <T> void testSignatureSimplePassThroughParameterization(final Class<T> targetClass,
                                                                          final Class<?> targetSuperInterface,
                                                                          final Map<String, Type> genericTypes) {
        testGenericDeclaration(targetClass, genericTypes);
        testGenericSuperInterface(targetClass, targetSuperInterface, targetClass.getTypeParameters());
    }

    public static void testGenericDeclaration(final GenericDeclaration declaration,
                                              final Map<String, Type> genericTypes) {
        final TypeVariable<?>[] typeParameters = declaration.getTypeParameters();
        Assertions.assertArrayEquals(genericTypes.keySet().toArray(new String[0]),
            Stream.of(typeParameters).map(Type::getTypeName).toArray(String[]::new),
            declaration + " has incorrect type parameters");
        genericTypes.forEach((name, bound) -> {
            final TypeVariable<?> typeParameter = Arrays.stream(typeParameters)
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Could not find type parameter " + name + " in " + declaration));
            final Type[] bounds = typeParameter.getBounds();
            Assertions.assertEquals(1, bounds.length,
                declaration + "'s type parameter " + name + " should not have additional bounds");
            Assertions.assertEquals(bound, bounds[0],
                declaration + "'s type parameter " + name + " should have upper bound " + bound);
        });
    }

    public static void testGenericParameters(final Method method,
                                             final Type... parameterTypes) {
        final Type[] actualParameterTypes = method.getGenericParameterTypes();
        Assertions.assertArrayEquals(parameterTypes, actualParameterTypes,
            method + " has incorrect parameter types");
    }

    public static void testGenericSuperInterface(final Class<?> targetClass,
                                                 final Class<?> targetSuperInterface,
                                                 final Type... genericParams) {
        Assertions.assertFalse(targetClass.isInterface(),
            targetClass + " should not be an interface");
        final String className = targetClass.getSimpleName();
        final String superInterfaceName = targetSuperInterface.getSimpleName();
        final String[] paramNames = Arrays.stream(genericParams).map(Type::getTypeName).toArray(String[]::new);
        final String params = String.join(", ", paramNames);
        final String superInterfaceSignature = superInterfaceName + "<" + params + ">";
        final Type[] interfaces = targetClass.getGenericInterfaces();
        Assertions.assertEquals(1, interfaces.length,
            "Expected " + className + " to implement exactly one interface " + superInterfaceSignature
                + ", but found " + Arrays.toString(interfaces));
        if (interfaces[0] instanceof final ParameterizedType parameterizedType) {
            Assertions.assertEquals(targetSuperInterface, parameterizedType.getRawType(),
                "Expected " + className + " to implement exactly one interface " + superInterfaceSignature
                    + ", but found " + parameterizedType);
            Assertions.assertEquals(paramNames.length, parameterizedType.getActualTypeArguments().length,
                "Expected " + className + " to parameterize " + superInterfaceName
                    + " with exactly " + paramNames.length + " type argument(s): (" + params + "), but found "
                    + Arrays.toString(parameterizedType.getActualTypeArguments()));
            for (int i = 0; i < genericParams.length; i++) {
                final Type actual = parameterizedType.getActualTypeArguments()[i];
                Assertions.assertEquals(genericParams[i], actual,
                    "Expected type parameter " + paramNames[i] + " to be parameterized at position " + i
                        + " in " + className + ", but found " + actual);
            }
        } else {
            Assertions.fail("Expected " + className + " to parameterize " + targetSuperInterface + " with " + params);
        }
    }
}
