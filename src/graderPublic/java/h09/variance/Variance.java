package h09.variance;

import java.lang.reflect.Type;

public enum Variance {
    INVARIANT,
    COVARIANT,
    CONTRAVARIANT;

    public String toString(final Type type) {
        return switch (this) {
            case INVARIANT -> type.getTypeName();
            case COVARIANT -> "? extends " + type.getTypeName();
            case CONTRAVARIANT -> "? super " + type.getTypeName();
        };
    }
}
