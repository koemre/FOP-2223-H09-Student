package h09.sequence.operation;

import h09.FieldExtensions;
import h09.sequence.Sequence;
import h09.variance.Variance;
import h09.variance.VarianceNode;
import h09.variance.VarianceTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.function.Predicate;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class FilteringSequenceIntermediateTest {

    @Test
    void testFields() {
        final TypeVariable<Class<FilteringSequence>> genericT = FilteringSequence.class.getTypeParameters()[0];
        final Field[] fields = FilteringSequence.class.getDeclaredFields();
        Assertions.assertEquals(2, fields.length, "FilteringSequence should have two fields");
        FieldExtensions.assertPrivateFinal("FilteringSequence", fields);

        final Field sequenceField = Stream.of(fields).filter(field -> field.getType().equals(Sequence.class)).findAny()
            .orElseThrow(() -> new AssertionError("FilteringSequence should have a field who's raw type is type Sequence"));
        final Field predicateField = Stream.of(fields).filter(field -> field.getType().equals(Predicate.class)).findAny()
            .orElseThrow(() -> new AssertionError("FilteringSequence should have a field who's raw type is type Predicate"));

        VarianceTestExtensions.assertLooseVariance(sequenceField.getGenericType(),
            new VarianceNode(genericT, Variance.COVARIANT));
        VarianceTestExtensions.assertLooseVariance(predicateField.getGenericType(),
            new VarianceNode(genericT, Variance.CONTRAVARIANT));
    }

    @Test
    void testConstructor() {
        final TypeVariable<Class<FilteringSequence>> genericT = FilteringSequence.class.getTypeParameters()[0];
        final Constructor<FilteringSequence> constructor = Assertions.assertDoesNotThrow(() ->
                FilteringSequence.class.getDeclaredConstructor(Sequence.class, Predicate.class),
            "FilteringSequence should have a constructor with parameters who's raw types are Sequence and Predicate");
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "FilteringSequence's constructor should be public");
        final Parameter[] parameters = constructor.getParameters();
        final Parameter sequenceParameter = parameters[0];
        final Parameter predicateParameter = parameters[1];
        VarianceTestExtensions.assertLooseVariance(sequenceParameter.getParameterizedType(),
            new VarianceNode(genericT, Variance.COVARIANT));
        VarianceTestExtensions.assertLooseVariance(predicateParameter.getParameterizedType(),
            new VarianceNode(genericT, Variance.CONTRAVARIANT));
    }
}
