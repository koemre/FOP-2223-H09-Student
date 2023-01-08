package h09;

import h09.basic.BasicBinaryOperationsTest;
import h09.basic.DoubleBasicBinaryOperationsTest;
import h09.basic.DoubleFactoryTest;
import h09.basic.IntegerBasicBinaryOperationsTest;
import h09.basic.IntegerFactoryTest;
import h09.basic.StringBasicBinaryOperationsTest;
import h09.basic.StringFactoryTest;
import h09.operator.ComposedBinaryOperatorTest;
import h09.operator.H2_1_Test;
import h09.operator.MaxOfTwoOperatorTest;
import h09.operator.SumWithCoefficientsOperatorTest;
import h09.sequence.ArraySequenceBasicTest;
import h09.sequence.BasicFactorySequenceBasicTest;
import h09.sequence.FibonacciSequenceBasicTest;
import h09.sequence.collect.BinaryOpFoldCollectorBasicTest;
import h09.sequence.collect.SummingCollectorBasicTest;
import h09.sequence.collect.ToListCollectorBasicTest;
import h09.sequence.operation.FilteringSequenceBasicTest;
import h09.sequence.operation.FilteringSequenceIntermediateTest;
import h09.sequence.operation.FlatteningTransformingSequenceBasicTest;
import h09.sequence.operation.FlatteningTransformingSequenceIntermediateTest;
import h09.sequence.operation.TransformingSequenceBasicTest;
import h09.sequence.operation.TransformingSequenceIntermediateTest;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import java.util.concurrent.Callable;

public class H09_RubricProvider implements RubricProvider {

    public static final Criterion H1_1 = createCriterion("H1.1 - IntegerFactory ist korrekt implementiert.", () -> IntegerFactoryTest.class);
    public static final Criterion H1_2 = createCriterion("H1.2 - DoubleFactory ist korrekt implementiert.", () -> DoubleFactoryTest.class);
    public static final Criterion H1_3 = createCriterion("H1.3 - StringFactory ist korrekt implementiert.", () -> StringFactoryTest.class);

    public static final Criterion H1_4_1 = createCriterion(
        "BasicBinaryOperations ist korrekt implementiert.",
        () -> BasicBinaryOperationsTest.class);

    public static final Criterion H1_4_2 = Criterion.builder()
        .shortDescription("DoubleBasicBinaryOperations und IntegerBasicBinaryOperations sind korrekt implementiert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> DoubleBasicBinaryOperationsTest.class))
            .requirePass(JUnitTestRef.ofClass(() -> IntegerBasicBinaryOperationsTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_4_3 = createCriterion(
        "StringBasicBinaryOperations ist korrekt implementiert.",
        () -> StringBasicBinaryOperationsTest.class);

    public static final Criterion H1_4 = Criterion.builder()
        .shortDescription("H1.4 - BasicBinaryOperations")
        .addChildCriteria(H1_4_1, H1_4_2, H1_4_3)
        .build();

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1 - BasicFactory")
        .addChildCriteria(H1_1, H1_2, H1_3, H1_4)
        .build();

    public static final Criterion H2_1 = createCriterion("H2.1 - Erster Satz von binären Operatorklassen ist korrekt implementiert.", () -> H2_1_Test.class);
    public static final Criterion H2_2 = createCriterion("H2.2 - ComposedBinaryOperator ist korrekt implementiert.", () -> ComposedBinaryOperatorTest.class);
    public static final Criterion H2_3 = createCriterion("H2.3 - MaxOfTwoOperator ist korrekt implementiert.", () -> MaxOfTwoOperatorTest.class);

    public static final Criterion H2_4_1 = Criterion.builder()
        .shortDescription("Klassensignatur, Attribute und Konstruktor von SumWithCoefficients sind korrekt implementiert.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> SumWithCoefficientsOperatorTest.class.getDeclaredMethod("testSignature")))
            .requirePass(JUnitTestRef.ofMethod(() -> SumWithCoefficientsOperatorTest.class.getDeclaredMethod("testFields")))
            .requirePass(JUnitTestRef.ofMethod(() -> SumWithCoefficientsOperatorTest.class.getDeclaredMethod("testConstructor")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_4_2 = Criterion.builder()
        .shortDescription("Die apply-Methode von SumWithCoefficients ist korrekt.")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> SumWithCoefficientsOperatorTest.class.getDeclaredMethod("testApplySignature")))
            .requirePass(JUnitTestRef.ofMethod(() -> SumWithCoefficientsOperatorTest.class.getDeclaredMethod("testApply")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_4 = Criterion.builder()
        .shortDescription("H2.4 - SumWithCoefficientsOperator")
        .addChildCriteria(H2_4_1, H2_4_2)
        .build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2 - Binary Operators")
        .addChildCriteria(H2_1, H2_2, H2_3, H2_4)
        .build();

    public static final Criterion H3_1_1 = createCriterion(
        "Klassensignatur von ArraySequence ist korrekt und der Iterator funktioniert für einfache Fälle.",
        () -> ArraySequenceBasicTest.class);

    public static final Criterion H3_1_2 = Criterion.builder()
        .shortDescription("ArraySequence ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H3_1 = Criterion.builder()
        .shortDescription("H3.1 - ArraySequence")
        .addChildCriteria(H3_1_1, H3_1_2)
        .build();

    public static final Criterion H3_2_1 = createCriterion(
        "Klassensignatur von FibonacciSequence ist korrekt und der Iterator funktioniert für einfache Fälle.",
        () -> FibonacciSequenceBasicTest.class);

    public static final Criterion H3_2_2 = Criterion.builder()
        .shortDescription("FibonacciSequence ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H3_2 = Criterion.builder()
        .shortDescription("H3.2 - FibonacciSequence")
        .addChildCriteria(H3_2_1, H3_2_2)
        .build();

    public static final Criterion H3_3_1 = createCriterion(
        "Klassensignatur von BasicFactorySequence ist korrekt.",
        () -> BasicFactorySequenceBasicTest.class);

    public static final Criterion H3_3_2 = Criterion.builder()
        .shortDescription("BasicFactorySequence ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H3_3 = Criterion.builder()
        .shortDescription("H3.3 - BasicFactorySequence")
        .addChildCriteria(H3_3_1, H3_3_2)
        .build();

    public static final Criterion H3 = Criterion.builder()
        .shortDescription("H3 - Sequences")
        .addChildCriteria(H3_1, H3_2, H3_3)
        .build();

    public static final Criterion H4_1_1 = createCriterion(
        "Klassensignatur von FilteringSequence ist korrekt und der Iterator funktioniert für einfache Fälle.",
        () -> FilteringSequenceBasicTest.class);

    public static final Criterion H4_1_2 = createCriterion(
        "Attribute und Konstruktor sind bis auf Wildcards korrekt implementiert.",
        () -> FilteringSequenceIntermediateTest.class);

    public static final Criterion H4_1_3 = Criterion.builder()
        .shortDescription("FilteringSequence ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H4_1 = Criterion.builder()
        .shortDescription("H4.1 - FilteringSequence")
        .addChildCriteria(H4_1_1, H4_1_2, H4_1_3)
        .build();

    public static final Criterion H4_2_1 = createCriterion(
        "Klassensignatur von TransformingSequence ist korrekt und der Iterator funktioniert für einfache Fälle.",
        () -> TransformingSequenceBasicTest.class);

    public static final Criterion H4_2_2 = createCriterion(
        "Attribute und Konstruktor von TransformingSequence sind bis auf Wildcards korrekt implementiert.",
        () -> TransformingSequenceIntermediateTest.class);

    public static final Criterion H4_2_3 = Criterion.builder()
        .shortDescription("TransformingSequence ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H4_2 = Criterion.builder()
        .shortDescription("H4.2 - TransformingSequence")
        .addChildCriteria(H4_2_1, H4_2_2, H4_2_3)
        .build();

    public static final Criterion H4_3_1 = createCriterion(
        "Klassensignatur von FlatteningTransformingSequence ist korrekt und der Iterator funktioniert für einfache Fälle.",
        () -> FlatteningTransformingSequenceBasicTest.class);

    public static final Criterion H4_3_2 = createCriterion(
        "Attribute und Konstruktor von FlatteningTransformingSequence sind bis auf Wildcards korrekt implementiert.",
        () -> FlatteningTransformingSequenceIntermediateTest.class);

    public static final Criterion H4_3_3 = Criterion.builder()
        .shortDescription("Generics von FlatteningTransformingSequence sind vollständig korrekt implementiert.")
        .build();

    public static final Criterion H4_3_4 = Criterion.builder()
        .shortDescription("FlatteningTransformingSequence ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H4_3 = Criterion.builder()
        .shortDescription("H4.3 - FlatteningTransformingSequence")
        .addChildCriteria(H4_3_1, H4_3_2, H4_3_3, H4_3_4)
        .build();

    public static final Criterion H4_4_1 = Criterion.builder()
        .shortDescription("FilteringSequence.of ist korrekt implementiert.")
        .build();

    public static final Criterion H4_4_2 = Criterion.builder()
        .shortDescription("TransformingSequence.of ist korrekt implementiert.")
        .build();

    public static final Criterion H4_4_3 = Criterion.builder()
        .shortDescription("FlatteningTransformingSequence.of ist korrekt implementiert.")
        .build();

    public static final Criterion H4_4 = Criterion.builder()
        .shortDescription("H4.4 - Einfachere Syntax bei Verwendung von Sequences")
        .addChildCriteria(H4_4_1, H4_4_2, H4_4_3)
        .build();

    public static final Criterion H4 = Criterion.builder()
        .shortDescription("H4 - Sequence Operationen")
        .addChildCriteria(H4_1, H4_2, H4_3, H4_4)
        .build();

    public static final Criterion H5_1_1 = createCriterion(
        "Klassensignatur von ToListCollector ist korrekt und funktioniert für einfache Fälle.",
        () -> ToListCollectorBasicTest.class);

    public static final Criterion H5_1_2 = Criterion.builder()
        .shortDescription("ToListCollector ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H5_1 = Criterion.builder()
        .shortDescription("H5.1 - ToListCollector")
        .addChildCriteria(H5_1_1, H5_1_2)
        .build();

    public static final Criterion H5_2_1 = createCriterion(
        "Klassensignatur von SummingCollector ist korrekt und funktioniert für einfache Fälle.",
        () -> SummingCollectorBasicTest.class);

    public static final Criterion H5_2_2 = Criterion.builder()
        .shortDescription("SummingCollector ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H5_2 = Criterion.builder()
        .shortDescription("H5.2 - SummingCollector")
        .addChildCriteria(H5_2_1, H5_2_2)
        .build();

    public static final Criterion H5_3_1 = createCriterion(
        "Klassensignatur von BinaryOpFoldCollector ist korrekt und funktioniert für einfache Fälle.",
        () -> BinaryOpFoldCollectorBasicTest.class);

    public static final Criterion H5_3_2 = Criterion.builder()
        .shortDescription("BinaryOpFoldCollector ist vollständig korrekt implementiert.")
        .build();

    public static final Criterion H5_3 = Criterion.builder()
        .shortDescription("H5.3 - BinaryOpFoldCollector")
        .addChildCriteria(H5_3_1, H5_3_2)
        .build();

    public static final Criterion H5 = Criterion.builder()
        .shortDescription("H5 - Collect-e")
        .addChildCriteria(H5_1, H5_2, H5_3)
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09 - Ein Einblick in Generics")
        .addChildCriteria(H1, H2, H3, H4, H5)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

    private static Criterion createCriterion(String shortDescription, Callable<Class<?>> testClassSupplier) {
        return Criterion.builder()
            .shortDescription(shortDescription)
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofClass(testClassSupplier))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
            .build();
    }
}
