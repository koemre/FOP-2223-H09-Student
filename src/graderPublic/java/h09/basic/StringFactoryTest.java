package h09.basic;

import h09.InvokeAssertions;
import h09.RandomExtensions;
import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.stream.IntStream;

@TestForSubmission
public final class StringFactoryTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureWithNonGenericBase(
            StringFactory.class, BasicFactory.class, String.class);
    }

    @Test
    void testSimple() {
        final int start = 1;
        final String[] input = {"Hallo", "Welt", "!"};
        final StringFactory factory = createInstance(start, input);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(input[(i + start) % input.length], invokeCreate(factory),
                "Failed for start = " + start + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 21, 64})
    void testComplex(final int start) {
        final Random random = new Random(start);
        final String[] input = IntStream.rangeClosed(0, 65).mapToObj(i -> RandomExtensions.generateString(random, i)).toArray(String[]::new);
        final StringFactory factory = createInstance(start, input);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(input[(i + start) % input.length], invokeCreate(factory),
                "Failed for start = " + start + " and i = " + i);
        }
    }

    private StringFactory createInstance(int start, String[] text) {
        final Constructor<StringFactory> constructor = Assertions.assertDoesNotThrow(() ->
                StringFactory.class.getDeclaredConstructor(int.class, String[].class),
            "StringFactory should have a constructor with int and String[] parameters");
        return InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(start, text),
            "Failed to invoke StringFactory constructor with int and String[] parameters");
    }

    private String invokeCreate(StringFactory factory) {
        final Method createMethod = Assertions.assertDoesNotThrow(() ->
                StringFactory.class.getDeclaredMethod("create"),
            "StringFactory should have a create method");
        return InvokeAssertions.assertDoesNotThrow(() ->
                (String) createMethod.invoke(factory),
            "Failed to invoke StringFactory create method");
    }
}
