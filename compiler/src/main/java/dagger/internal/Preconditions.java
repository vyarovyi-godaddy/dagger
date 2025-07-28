package dagger.internal;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 */
public final class Preconditions {

    /** Ensures that an object reference passed as a parameter to the calling method is not null. */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /** Ensures that an object reference passed as a parameter to the calling method is not null. */
    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    /** Ensures that an object reference passed as a parameter to the calling method is not null. */
    public static Object checkNotNull(Object reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

    /** Ensures that an object reference passed as a parameter to the calling method is not null. */
    public static <T> T checkNotNull(
            T reference, String errorMessageTemplate, Object... errorMessageArgs) {
        if (reference == null) {
            throw new NullPointerException(String.format(errorMessageTemplate, errorMessageArgs));
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     */
    public static void checkArgument(
            boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(
                    String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /** Ensures the truth of an expression involving the state of the calling instance. */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /** Ensures the truth of an expression involving the state of the calling instance. */
    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    /** Ensures the truth of an expression involving the state of the calling instance. */
    public static void checkState(
            boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalStateException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * Checks that a required builder component is set. Used by Dagger generated code to validate
     * builder requirements.
     */
    public static void checkBuilderRequirement(Object component, Class<?> componentClass) {
        if (component == null) {
            throw new IllegalStateException(componentClass.getCanonicalName() + " must be set");
        }
    }

    private Preconditions() {
        throw new AssertionError("No instances");
    }
}
