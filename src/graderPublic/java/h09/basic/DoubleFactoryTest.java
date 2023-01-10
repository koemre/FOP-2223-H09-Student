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
public final class DoubleFactoryTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureWithNonGenericBase(
            DoubleFactory.class, BasicFactory.class, Double.class);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1, 3.5, 5.75, 2000.5})
    void testSimple(final double step) {
        final DoubleFactory factory = createInstance(1, step);
        for (double i = 0; i < 500; i++) {
            Assertions.assertEquals(1 + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1, 3.5, 5.75, 2000.5})
    void testNegativeStart(final double step) {
        final double start = -143.5;
        final DoubleFactory factory = createInstance(start, step);
        for (double i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.5, -2.25, -5.25, -1500.5})
    void testNegativeStep(final double step) {
        final double start = 100;
        final DoubleFactory factory = createInstance(start, step);
        for (double i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.5, 1.5, -5.25, 5.25, -1500.5, 1500.5})
    void testAll(final double step) {
        final double start = -7.5;
        final DoubleFactory factory = createInstance(start, step);
        for (double i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, invokeCreate(factory),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    private DoubleFactory createInstance(double start, double step) {
        final Constructor<DoubleFactory> constructor = Assertions.assertDoesNotThrow(() ->
                DoubleFactory.class.getDeclaredConstructor(double.class, double.class),
            "DoubleFactory should have a constructor with two double parameters");
        return InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(start, step),
            "Failed to invoke DoubleFactory constructor with two double parameters");
    }

    private Double invokeCreate(DoubleFactory factory) {
        final Method createMethod = Assertions.assertDoesNotThrow(() ->
                DoubleFactory.class.getDeclaredMethod("create"),
            "DoubleFactory should have a create method");
        return InvokeAssertions.assertDoesNotThrow(() ->
                (Double) createMethod.invoke(factory),
            "Failed to invoke DoubleFactory create method");
    }
}
