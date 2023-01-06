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
import java.util.function.Function;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class FlatteningTransformingSequenceIntermediateTest {

    @Test
    void testFields() {
        final TypeVariable<Class<FlatteningTransformingSequence>>[] typeParameters = FlatteningTransformingSequence.class.getTypeParameters();
        final TypeVariable<Class<FlatteningTransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<FlatteningTransformingSequence>> genericR = typeParameters[1];
        final Field[] fields = FlatteningTransformingSequence.class.getDeclaredFields();
        Assertions.assertEquals(2, fields.length, "FlatteningTransformingSequence should have two fields");
        FieldExtensions.assertPrivateFinal("FlatteningTransformingSequence", fields);

        final Field sequenceField = Stream.of(fields).filter(field -> field.getType().equals(Sequence.class)).findAny()
            .orElseThrow(() -> new AssertionError("FlatteningTransformingSequence should have a field who's raw type is type Sequence"));
        final Field functionField = Stream.of(fields).filter(field -> field.getType().equals(Function.class)).findAny()
            .orElseThrow(() -> new AssertionError("FlatteningTransformingSequence should have a field who's raw type is type Sequence"));

        VarianceTestExtensions.assertLooseVariance(sequenceField.getGenericType(),
            new VarianceNode(genericT, Variance.COVARIANT));
        VarianceTestExtensions.assertLooseVariance(functionField.getGenericType(),
            new VarianceNode(genericT, Variance.CONTRAVARIANT),
            new VarianceNode(Sequence.class, Variance.COVARIANT, new VarianceNode(genericR, Variance.COVARIANT)));
    }

    @Test
    void testConstructor() {
        final TypeVariable<Class<FlatteningTransformingSequence>>[] typeParameters = FlatteningTransformingSequence.class.getTypeParameters();
        final TypeVariable<Class<FlatteningTransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<FlatteningTransformingSequence>> genericR = typeParameters[1];
        final Constructor<FlatteningTransformingSequence> constructor = Assertions.assertDoesNotThrow(() ->
            FlatteningTransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class));
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "FlatteningTransformingSequence's constructor should be public");
        final Parameter[] parameters = constructor.getParameters();
        final Parameter sequenceParameter = parameters[0];
        final Parameter functionParameter = parameters[1];

        VarianceTestExtensions.assertLooseVariance(sequenceParameter.getParameterizedType(),
            new VarianceNode(genericT, Variance.COVARIANT));
        VarianceTestExtensions.assertLooseVariance(functionParameter.getParameterizedType(),
            new VarianceNode(genericT, Variance.CONTRAVARIANT),
            new VarianceNode(Sequence.class, Variance.COVARIANT, new VarianceNode(genericR, Variance.COVARIANT)));
    }
}
