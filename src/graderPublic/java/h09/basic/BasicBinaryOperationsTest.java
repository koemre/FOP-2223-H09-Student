package h09.basic;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.LinkedHashMap;
import java.util.Map;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class BasicBinaryOperationsTest {

    @Test
    void testClassSignature() {
        // linked hash map to preserve insertion order
        final Map<String, Type> genericParams = new LinkedHashMap<>();
        genericParams.put("X", Object.class);
        genericParams.put("Y", Object.class);
        SignatureTestExtensions.testGenericDeclaration(BasicBinaryOperations.class, genericParams);
    }

    @Test
    void testAddSignature() {
        final Method method = Assertions.assertDoesNotThrow(() ->
                BasicBinaryOperations.class.getDeclaredMethod("add", Object.class, Object.class),
            "Could not find add method in BasicBinaryOperations");
        final TypeVariable<Class<BasicBinaryOperations>>[] typeParameters = BasicBinaryOperations.class.getTypeParameters();
        Assertions.assertEquals(typeParameters[0], method.getGenericReturnType(),
            "add's return type should be X");
        SignatureTestExtensions.testGenericParameters(method, typeParameters[0], typeParameters[0]);
    }

    @Test
    void testMulSignature() {
        final Method method = Assertions.assertDoesNotThrow(() ->
                BasicBinaryOperations.class.getDeclaredMethod("mul", Object.class, Object.class),
            "Could not find mul method in BasicBinaryOperations");
        final TypeVariable<Class<BasicBinaryOperations>>[] typeParameters = BasicBinaryOperations.class.getTypeParameters();
        Assertions.assertEquals(typeParameters[0], method.getGenericReturnType(),
            "mul's return type should be X");
        SignatureTestExtensions.testGenericParameters(method, typeParameters);
    }
}
