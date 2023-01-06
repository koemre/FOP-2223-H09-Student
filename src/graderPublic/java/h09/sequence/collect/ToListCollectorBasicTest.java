package h09.sequence.collect;

import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import io.leangen.geantyref.TypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class ToListCollectorBasicTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<ToListCollector>>[] typeParameters = ToListCollector.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "ToListCollector should have a type parameter T");
        final TypeVariable<Class<ToListCollector>> genericT = typeParameters[0];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "ToListCollector's generic type T should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(ToListCollector.class, SequenceCollector.class, genericT,
            TypeFactory.parameterizedClass(List.class, genericT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testCollectBasic() {
        final List<String> list = Sequence.of("one", "two", "three")
            .collect((SequenceCollector<String, ? extends List<String>>) new ToListCollector());
        Assertions.assertArrayEquals(new String[]{"one", "two", "three"}, list.toArray(new String[0]));
    }
}
