package h09;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class FieldExtensions {

    private FieldExtensions() {
    }

    public static void assertPrivateFinal(final String className, final Field[] fields) {
        for (final Field field : fields) {
            Assertions.assertTrue(Modifier.isFinal(field.getModifiers()),
                className + "." + field.getName() + " should be final");
            Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()),
                className + "." + field.getName() + " should be private");
        }
    }
}
