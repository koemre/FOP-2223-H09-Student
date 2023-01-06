package h09.variance;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public record VarianceNode(Type expectedBound, Variance expectedVariance, VarianceNode... children) {
    public void check(final Type actualType, final boolean strict) {
        try {
            switch (expectedVariance) {
                case INVARIANT -> assertInvariantType(actualType);
                case COVARIANT -> assertCovariantType(actualType);
                case CONTRAVARIANT -> assertContravariantType(actualType);
            }
        } catch (AssertionFailedError e) {
            if (strict) {
                throw e;
            }
            final Type normalizedExpectedBound = normalizeType(expectedBound);
            Assertions.assertTrue(matchesInvariant(actualType, normalizedExpectedBound),
                "Type " + actualType + " does not match expected type "
                    + expectedVariance.toString(expectedBound) + " or raw type " + normalizedExpectedBound);
        }
        // now check the children recursively if this is a parameterized type
        final Type normalizedActualType = dewildcardType(actualType);
        if (!(normalizedActualType instanceof final ParameterizedType actualParameterizedType)) {
            if (children.length > 0) {
                throw new AssertionError("Expected type " + normalizedActualType
                    + " to be parameterized, but it is not");
            } else {
                return;
            }
        }
        Assertions.assertEquals(children.length, actualParameterizedType.getActualTypeArguments().length,
            "Expected type " + expectedVariance.toString(expectedBound) + " to have " + children.length
                + " type arguments, but it has " + actualParameterizedType.getActualTypeArguments().length);
        for (int i = 0; i < children.length; i++) {
            children[i].check(actualParameterizedType.getActualTypeArguments()[i], strict);
        }
    }

    private boolean matchesInvariant(final Type actualType, final Type normalizedExpectedBound) {
        if (actualType instanceof WildcardType) {
            return false;
        } else if (actualType instanceof ParameterizedType actualParameterizedType) {
            return normalizedExpectedBound.equals(actualParameterizedType.getRawType());
        } else {
            return normalizedExpectedBound.equals(actualType);
        }
    }

    private void assertInvariantType(final Type type) {
        if (type instanceof final WildcardType wildcardType) {
            if (wildcardType.getLowerBounds().length == 0) {
                Assertions.fail("Expected invariant type parameter, but found covariant wildcard type parameter " + wildcardType);
            } else {
                Assertions.fail("Expected invariant type parameter, but found contravariant wildcard type parameter " + wildcardType);
            }
        } else {
            Assertions.assertEquals(expectedBound, type,
                "Expected invariant type parameter" + expectedBound + ", but found " + type);
        }
    }

    private void assertCovariantType(final Type type) {
        if (!(type instanceof final WildcardType wildcardType)) {
            throw new AssertionFailedError("Expected covariant type parameter, but found invariant type parameter " + type);
        }
        final Type[] lowerBounds = wildcardType.getLowerBounds();
        final Type[] upperBounds = wildcardType.getUpperBounds();
        if (lowerBounds.length == 0) {
            final Type rawUpperBound = upperBounds[0] instanceof ParameterizedType parameterizedType
                ? parameterizedType.getRawType()
                : upperBounds[0];
            Assertions.assertEquals(expectedBound, rawUpperBound,
                "Expected covariant type parameter, but found wildcard type parameter with incorrect upper bound " + wildcardType);
        } else {
            Assertions.fail("Expected covariant type parameter, but found contravariant wildcard type parameter " + wildcardType);
        }
    }

    private void assertContravariantType(final Type type) {
        if (!(type instanceof final WildcardType wildcardType)) {
            throw new AssertionFailedError("Expected contravariant type parameter, but found invariant type parameter " + type);
        }
        final Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length == 0) {
            Assertions.fail("Expected contravariant type parameter, but found contravariant wildcard type parameter " + wildcardType);
        } else {
            final Type rawLowerBound = lowerBounds[0] instanceof ParameterizedType parameterizedType
                ? parameterizedType.getRawType()
                : lowerBounds[0];
            Assertions.assertEquals(expectedBound, rawLowerBound,
                "Expected contravariant type parameter, but found wildcard type parameter with incorrect lower bound " + wildcardType);
        }
    }

    private static Type normalizeType(final Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            return parameterizedType.getRawType();
        }
        return dewildcardType(type);
    }

    private static Type dewildcardType(final Type type) {
        if (type instanceof WildcardType wildcardType) {
            final Type[] lowerBounds = wildcardType.getLowerBounds();
            final Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 0) {
                return upperBounds[0];
            } else {
                return lowerBounds[0];
            }
        } else {
            return type;
        }
    }
}
