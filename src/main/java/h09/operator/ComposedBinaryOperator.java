package h09.operator;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

// weil binary operator (bereits generisch definiert) mit T (unbekannter Typ) instanziiert werden soll
// muss die Klasse auch mit T parametarisiert sein, damit T als unbekannt erkannt werden kann
public class ComposedBinaryOperator <T> implements BinaryOperator <T> {

    @Override
    public T apply(T left, T right) {
            // Apply first operator to given parameters
            T result1 = op1.apply(left, right);

            // Apply second operator to given parameters
            T result2 = op2.apply(left, right);

            // Return application of third operator to the intermediate results
            T result3 = op3.apply(result1, result2);
        return result3;
    }

        /**
         * First operator.
         */
        private final BinaryOperator<T> op1;

        /**
         * Second operator.
         */
        private final BinaryOperator<T> op2;

        /**
         * Third operator.
         */
        private final BinaryOperator<T> op3;

        /**
         * Constructor initializes the three operators.
         *
         * @param op1 First operator.
         * @param op2 Second operator.
         * @param op3 Third operator.
         */
        public ComposedBinaryOperator(BinaryOperator<T> op1, BinaryOperator<T> op2, BinaryOperator<T> op3) {
            // Assign first parameter to first operator
            this.op1 = op1;

            // Assign second parameter to second operator
            this.op2 = op2;

            // Assign third parameter to third operator
            this.op3 = op3;
        }

        /**
         * First applies the first and the second operator to the given parameters.
         * Then applies the third operator to the result of the application of the
         * first two operators.
         *
         * @param left  The first parameter.
         * @param right The second parameter.
         * @return Application of the third operator on the results of the
         * first and second operator.
         */

    }

