package h09.basic;

import h09.InvokeAssertions;
import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@TestForSubmission
public final class IntegerFactoryTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureWithNonGenericBase(
            IntegerFactory.class, BasicFactory.class, Integer.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 2000})
    void testSimple(final int step) {
        final IntegerFactory factory = createInstance(1, step);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(1 + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 2000})
    void testNegativeStart(final int step) {
        final int start = -192;
        final IntegerFactory factory = createInstance(start, step);
        for (int i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -5, -2000})
    void testNegativeStep(final int step) {
        final int start = 100;
        final IntegerFactory factory = createInstance(start, step);
        for (int i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, -5, 5, -2000, 2000})
    void testAll(final int step) {
        final int start = -7;
        final IntegerFactory factory = createInstance(start, step);
        for (int i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    private IntegerFactory createInstance(int start, int step) {
        final Constructor<IntegerFactory> constructor = Assertions.assertDoesNotThrow(() ->
                IntegerFactory.class.getDeclaredConstructor(int.class, int.class),
            "IntegerFactory should have a constructor with two int parameters");
        return InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(start, step),
            "Failed to invoke IntegerFactory constructor with two int parameters");
    }

    private Integer invokeCreate(IntegerFactory factory) {
        final Method createMethod = Assertions.assertDoesNotThrow(() ->
                IntegerFactory.class.getDeclaredMethod("create"),
            "IntegerFactory should have a create method");
        return InvokeAssertions.assertDoesNotThrow(() ->
                (Integer) createMethod.invoke(factory),
            "Failed to invoke IntegerFactory create method");
    }
}
