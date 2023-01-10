package h09.operator;

import h09.InvokeAssertions;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;

public final class BinaryOperatorInvoker {

    private BinaryOperatorInvoker() {
    }

    static Object invokeApply(final Object operations, final Object left, final Object right) {
        final Method method = Assertions.assertDoesNotThrow(() ->
                operations.getClass().getDeclaredMethod("apply", Object.class, Object.class),
            "Could not find apply method in " + operations.getClass().getSimpleName());
        return InvokeAssertions.assertDoesNotThrow(() -> method.invoke(operations, left, right),
            "Could not invoke apply method in " + operations.getClass().getSimpleName());
    }
}
