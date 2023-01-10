package h09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.lang.reflect.InvocationTargetException;

public final class InvokeAssertions {

    private InvokeAssertions() {
    }

    public static <T> T assertDoesNotThrow(final ThrowingSupplier<T> supplier, final String message) {
        return Assertions.assertDoesNotThrow(() -> {
            try {
                return supplier.get();
            } catch (final InvocationTargetException e) {
                throw e.getCause();
            }
        }, message);
    }
}
